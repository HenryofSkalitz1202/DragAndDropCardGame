package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Makhluk.Exception.BelumSiapPanenException;
import com.hbd.Kartu.Makhluk.Exception.ItemTidakAdaException;
import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;

public abstract class Hewan extends Makhluk {

    public Hewan(String nama, int maksPanen, String hasilPanen) {
        super(nama, maksPanen, hasilPanen);
    }

    public int getBerat() {
        return this.getProgressPanen();
    }

    public void tambahBerat(int tambahanBerat) {
        this.setProgressPanen(this.getProgressPanen() + tambahanBerat);
    }

    public int getBeratPanen() {
        return this.getMaksPanen();
    }

    @Override
    public ProdukHewan panen() throws BelumSiapPanenException {
        if (!siapPanen()) {throw new BelumSiapPanenException("Hewan ini belum siap panen");}
        return (ProdukHewan) FactoryKartu.getKartu(this.getHasilPanen());
    }

    @Override
    public void hisabItem(Item item) throws ItemTidakAdaException{
        if (item.getNama().equals("Accelerate")){
            setProgressPanen(getProgressPanen() + 8);
            getEffect().add("Accelerate");
        } else if (item.getNama().equals("Delay")) {
            setProgressPanen(getProgressPanen() - 5);
            if (getProgressPanen() < 0) {setProgressPanen(0);}
            getEffect().add("Delay");
        } else if (item.getNama().equals("Protect") || item.getNama().equals("Trap")) {
            getEffect().add(item.getNama());
        } else{
            throw new ItemTidakAdaException("Jenis Item Illegal");
        }
    }

    public abstract void makan(Produk makanan) throws SalahMakanException;
}
