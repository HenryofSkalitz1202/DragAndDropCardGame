package com.hbd.Kartu;

public abstract class Kartu {
    private final String nama;
    
    public Kartu(String nama){
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }
}
