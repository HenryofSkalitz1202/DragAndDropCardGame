package com.hbd.Kartu.Produk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdukTest {

    @Test
    public void ProdukConstructorTest(){
        Produk testProduk = new Produk("AlohaWorld", 100, 55);
        assertTrue(testProduk.getNama().equals("AlohaWorld"), () -> "Kontruktor Produk gagal set nama");
        assertTrue(testProduk.getHarga() == 100, () -> "Kontruktor Produk gagal set harga");
        assertTrue(testProduk.getTambahanBerat() == 55, () -> "Konstruktor Produk gagal set tambahanBerat");
    }
}