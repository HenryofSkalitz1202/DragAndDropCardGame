package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Kartu;

public abstract class Makhluk extends Kartu {
    private int progressPanen;
    private final int maksPanen;

    public Makhluk(String nama, int maksPanen){
        super(nama);
        this.progressPanen = 0;
        this.maksPanen = maksPanen;
    }

    public int getProgressPanen() {
        return this.progressPanen;
    }

    public int getMaksPanen() {
        return this.maksPanen;
    }

    public void setProgressPanen(int progressPanen) {
        this.progressPanen = progressPanen;
    }
}
