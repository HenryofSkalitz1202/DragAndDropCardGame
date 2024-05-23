package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Makhluk.Exception.BelumSiapPanenException;
import com.hbd.Kartu.Makhluk.Exception.ItemTidakAdaException;
import com.hbd.Kartu.Produk.ProdukTanaman;
import com.hbd.Kartu.FactoryKartu;

public class Tanaman extends Makhluk {

    public Tanaman(String nama, int maksPanen, String hasilPanen) {
        super(nama, maksPanen, hasilPanen);
    }

    public int getUmur() {
        return this.getProgressPanen();
    }

    public void tambahUmurSatu() {
        this.setProgressPanen(this.getProgressPanen() + 1);
    }

    public int getUmurPanen() {
        return this.getMaksPanen();
    }

    @Override
    public void hisabItem(Item item) throws ItemTidakAdaException {
        if (item.getNama().equals("Accelerate")){
            setProgressPanen(getProgressPanen() + 2);
            getEffect().add("Accelerate");
        } else if (item.getNama().equals("Delay")) {
            setProgressPanen(getProgressPanen() - 2);
            if (getProgressPanen() < 0) {setProgressPanen(0);}
            getEffect().add("Delay");
        } else if (item.getNama().equals("Protect") || item.getNama().equals("Trap")) {
            getEffect().add(item.getNama());
        } else{
            throw new ItemTidakAdaException("Jenis Item Illegal");
        }
    }

    @Override
    public ProdukTanaman panen() throws BelumSiapPanenException {
        if (!siapPanen()) {throw new BelumSiapPanenException("Tanaman ini belum siap panen");}
        return (ProdukTanaman) FactoryKartu.getKartu(this.getHasilPanen());
    }

}
