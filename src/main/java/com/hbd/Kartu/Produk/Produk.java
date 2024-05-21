package com.hbd.Kartu.Produk;

import com.hbd.Kartu.Kartu;
import javafx.scene.shape.Rectangle;

public class Produk extends Kartu {
    private final int harga;
    private final int tambahanBerat;

    public Produk(String nama, int harga, int tambahanBerat) {
        super(nama, 1, 2, 3, 4, new Rectangle());
        this.harga = harga;
        this.tambahanBerat = tambahanBerat;
    }

    public int getHarga() {
        return this.harga;
    }

    public int getTambahanBerat() {
        return this.tambahanBerat;
    }
}
