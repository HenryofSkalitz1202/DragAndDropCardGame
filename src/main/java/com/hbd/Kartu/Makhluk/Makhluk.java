package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Produk.Produk;

public abstract class Makhluk extends Kartu {
    private int progressPanen;
    private final int maksPanen;
    private final String hasilPanen;

    public Makhluk(String nama, String imagePath, int maksPanen, String hasilPanen) {
        super(nama, imagePath);
        this.progressPanen = 0;
        this.maksPanen = maksPanen;
        this.hasilPanen = hasilPanen;
    }

    public int getProgressPanen() {
        return this.progressPanen;
    }

    public int getMaksPanen() {
        return this.maksPanen;
    }

    public String getHasilPanen() {
        return this.hasilPanen;
    }

    public void setProgressPanen(int progressPanen) {
        this.progressPanen = progressPanen;
    }

    public abstract Produk panen();
}
