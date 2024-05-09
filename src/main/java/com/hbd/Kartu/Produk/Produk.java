package com.hbd.Kartu.Produk;

import com.hbd.Kartu.Kartu;

public class Produk extends Kartu {
    private final int harga;

    public Produk(String nama, int harga){
        super(nama);
        this.harga = harga;
    }

    public int getHarga() {
        return this.harga;
    }
}
