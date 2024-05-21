package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Produk.ProdukHewan;

public abstract class Hewan extends Makhluk {

    public Hewan(String nama, String imagePath, int maksPanen, String hasilPanen) {
        super(nama, imagePath, maksPanen, hasilPanen);
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
    public ProdukHewan panen() {
        return (ProdukHewan) FactoryKartu.getKartu(this.getHasilPanen());
    }
}
