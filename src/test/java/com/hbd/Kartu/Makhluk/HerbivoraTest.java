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

class HerbivoraTest {
    
    @Test
    
    void TestMakan(){
        Herbivora ulat = new Herbivora("Ulat", 6, "Sutra");

        assertThrows(SalahMakanException.class, () -> ulat.makan(new Produk("Bob", 13, 5)), () -> "Herbivora gagal menolak memakan Produk Base Class");
        assertThrows(SalahMakanException.class, () -> ulat.makan(new ProdukHewan("Bob", 13, 5)), () -> "Herbivora gagal menolak memakan ProdukHewan");

        Produk produk = new ProdukTanaman("Bobby", 13, 2);
        try{
            ulat.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (herbivora) gagal memvalidasi produk tanaman sebagai makanan yang valid");
        }

        assertTrue(ulat.getBerat() == 2, "makan (herbivora) gagal menambah berat herbivora");

        produk = (Produk) new ProdukTanaman("Leaves", -1, 1);

        try{
            ulat.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (herbivora) gagal memvalidasi produk tanaman yang di cast ke produk sebagai makanan yang valid");
        }
    }
}