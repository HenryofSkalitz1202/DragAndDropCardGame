package com.hbd;

import java.util.ArrayList;

import com.hbd.Kartu.Makhluk.Makhluk;

public class PetakLadang {
    private ArrayList<ArrayList<Makhluk>> ladang;

    public PetakLadang() {
        this.ladang = new ArrayList<ArrayList<Makhluk>>();
    }

    public ArrayList<ArrayList<Makhluk>> getLadang() {
        return this.ladang;
    }

    public Makhluk getMakhuk(int x, int y) {
        return this.ladang.get(x).get(y);
    }

    public void setMakhluk(int x, int y, Makhluk makhluk) {
        this.ladang.get(x).set(y, makhluk);
    }
}
