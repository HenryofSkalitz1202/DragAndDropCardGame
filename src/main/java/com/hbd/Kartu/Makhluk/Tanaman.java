package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Produk.ProdukTanaman;
import com.hbd.Kartu.FactoryKartu;

public class Tanaman extends Makhluk {

    public Tanaman(String nama, String imagePath, int maksPanen, String hasilPanen) {
        super(nama, imagePath, maksPanen, hasilPanen);
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
    public ProdukTanaman panen() {
        return (ProdukTanaman) FactoryKartu.getKartu(this.getHasilPanen());
    }

}
