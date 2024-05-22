package com.hbd.Pemain;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Exception.BelumSiapPanenException;
import com.hbd.Kartu.Makhluk.Exception.BerusahaMemberiItemKeMakhlukGaibException;
import com.hbd.Kartu.Makhluk.Exception.ItemTidakAdaException;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Makhluk.Tanaman;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Pemain.Exception.BerusahaPanenMakhlukGaibException;
import com.hbd.Pemain.Exception.IllegalItemException;
import com.hbd.Pemain.Exception.TidakMampuBeliException;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;
import com.hbd.Toko.Exception.ProdukTidakDijualException;
import com.hbd.Toko.Exception.TokoHanyaMembeliProdukException;
import com.hbd.Toko.Toko;

public class Pemain {
    private Deck deck;
    private Deck deckAktif;
    private PetakLadang petakLadang;
    private int duit;

    public Pemain(){
        deck = new Deck();
        deckAktif = new Deck();
        petakLadang = new PetakLadang();
        duit = 0;
    }

    public Deck getDeck(){
        return deck;
    }

    public Deck getDeckAktif(){
        return deckAktif;
    }

    public PetakLadang getPetakLadang(){return petakLadang;}

    public int getDuit(){
        return duit;
    }

    public void setDuit(int newDuit){
        duit = newDuit;
    }

    public void tambahDuit(int extraDuit) {duit += extraDuit;}

    public void pakaiDuit(int duitDipake) {duit -= duitDipake;}

    public void beriItemKeLadang(Pemain aktor, Item item, int x, int y) throws DiluarPetakException, DeckPenuhException, IllegalItemException, ItemTidakAdaException, BerusahaMemberiItemKeMakhlukGaibException {

        // DiluarPetakException hanya mungkin di throw dari sini
        Makhluk korban = petakLadang.getMakhluk(x, y);

        // Makhluk Gaib dihandle di sini
        if (korban == null) {
            throw new BerusahaMemberiItemKeMakhlukGaibException("Anda berusaha memberi item ke makluk gaib");
        }

        if (aktor != this){
            if (!(item.getNama().equals("Destroy") || item.getNama().equals("Delay"))){
                throw new IllegalItemException("Anda tidak boleh menggunakan item itu pada ladang ini");
            }
        }

        if (item.getNama().equals("Instant Harvest")){
            if (deckAktif.remainingSlot() == 0) {throw new DeckPenuhException("Deck aktif penuh");}

            petakLadang.getMakhluk(x, y).setProgressPanen(petakLadang.getMakhluk(x, y).getMaksPanen());
            try {
                panenPetak(x, y);
            } catch (Exception e){/* Tidak mungkin terjadi */}
        } else if (item.getNama().equals("Destroy")) {
            petakLadang.setMakhluk(x, y, null);
        } else{
            petakLadang.getMakhluk(x, y).hisabItem(item);
        }
    }

    public void panenPetak(int x, int y) throws DiluarPetakException, BerusahaPanenMakhlukGaibException, BelumSiapPanenException, DeckPenuhException {
        Makhluk korban = petakLadang.getMakhluk(x, y);
        if (korban != null){
            Produk hasilPanen = korban.panen();
            deckAktif.insertKartu(hasilPanen);
            petakLadang.setMakhluk(x, y, null);
        } else{
            throw new BerusahaPanenMakhlukGaibException("Anda berusaha untuk memanen makhluk gaib");
        }
    }

    public void BeliProdukKeToko(Toko toko, String namaProduk) throws TidakMampuBeliException, ProdukTidakDijualException, DeckPenuhException {

        // Produk Tidak Dijual Exception dithrow dari sini
        Produk produk = toko.beliItem(namaProduk);
        try{
            if (produk.getHarga() > duit){throw new TidakMampuBeliException("Uang anda tidak cukup untuk membeli itu");}
            pakaiDuit(produk.getHarga());
            deckAktif.insertKartu(produk);
        } catch (TidakMampuBeliException e){
            toko.tambahItemKeToko(produk);
            throw e;
        } catch (DeckPenuhException e){
            try {
                tambahDuit(toko.jualItem(produk.getNama()));
            } catch (TokoHanyaMembeliProdukException e2) {/* Tidak Mungkin Terjadi */}
            throw e;
        }
    }

    public void JualProdukKeToko(Toko toko, int indexDeckAktif) throws DeckEmptyException, DeckOutOfBoundsException, TokoHanyaMembeliProdukException {

        // Deck Empty dan DeckOutOfBound dithrow dari sini
        Kartu KartuDijual = deckAktif.takeKartuAt(indexDeckAktif);

        try {
            int uangDidapat = toko.jualItem(KartuDijual.getNama());
            tambahDuit(uangDidapat);
        } catch (TokoHanyaMembeliProdukException e){
            /*
             * Block ini tercapai jika berusaha menjual item yang bukan produk
             */
            try{
                deckAktif.insertKartu(KartuDijual);
            } catch (DeckPenuhException e2) {/* Tidak mungkin terjadi */}
            throw e;
        }
    }

    public void updateUmurPetak(){
        for(Makhluk tanaman : petakLadang){
            if (tanaman.getClass() == Tanaman.class){
                ((Tanaman) tanaman).tambahUmurSatu();
            }
        }
    }
}