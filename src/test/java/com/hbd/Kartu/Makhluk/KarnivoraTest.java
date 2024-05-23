package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.Kartu.Produk.ProdukTanaman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dependency : Produk, Hewan
 */

class KarnivoraTest {
    @Test

    void TestMakan(){
        Karnivora piranha = new Karnivora("Piranha", 6, "Gigi Piranha");

        assertThrows(SalahMakanException.class, () -> piranha.makan(new Produk("Bob", 13, 5)), () -> "Karnivora gagal menolak memakan Produk Base Class");
        assertThrows(SalahMakanException.class, () -> piranha.makan(new ProdukTanaman("Sudowoodo", 13, 999)), () -> "Karnivora gagal menolak memakan ProdukTanaman");

        Produk produk = new ProdukHewan("Piranha Juga", 3, 8);
        try{
            piranha.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (karnivora) gagal memvalidasi produk hewan sebagai makanan yang valid");
        }

        assertTrue(piranha.getBerat() == 8, "makan (karnivora) gagal menambah berat karnivora");

        produk = (Produk) new ProdukHewan("bob", -1, 1);

        try{
            piranha.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (karnivora) gagal memvalidasi produk hewan yang di cast ke produk sebagai makanan yang valid");
        }
    }
}