package com.hbd.Kartu.Makhluk;

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

}
