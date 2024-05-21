package com.hbd.Kartu;

public abstract class Kartu {
    private final String nama;
    private String imagePath;

    public Kartu(String nama, String imagePath){
        this.nama = nama;
        this.imagePath = imagePath;
    }

    public String getNama() {
        return this.nama;
    }

    public String getImagePath(){
        return this.imagePath;
    }
}
