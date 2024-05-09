package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Kartu;

public abstract class Makhluk extends Kartu {
    private int progressPanen;
    private final int maksPanen;
    private final String hasilPanen;

    public Makhluk(String nama, int maksPanen, String hasilPanen) {
        super(nama);
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
}
