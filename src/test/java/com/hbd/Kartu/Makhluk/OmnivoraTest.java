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

class OmnivoraTest {

    @Test
    public void TestMakan(){
        Omnivora hiu = new Omnivora("Hiu", 6, "Tuna");

        assertThrows(SalahMakanException.class, () -> hiu.makan(new Produk("Bob", 13, 5)), () -> "Omnivora gagal menolak memakan Produk Base Class");

        Produk produk = new ProdukHewan("Bob", 13, 5);
        try{
            hiu.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (omnivora) gagal memvalidasi produk hewan sebagai makanan yang valid");
        }

        assertTrue(hiu.getBerat() == 5, "makan (omnivora) gagal menambah berat omnivora");

        produk = new ProdukTanaman("Bobby", 13, 2);
        try{
            hiu.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (omnivora) gagal memvalidasi produk tanaman sebagai makanan yang valid");
        }

        assertTrue(hiu.getBerat() == 7, "makan (omnivora) gagal menambah berat omnivora");

        produk = (Produk) new ProdukTanaman("Leaves", -1, 1);

        try{
            hiu.makan(produk);
        } catch (Exception e) {
            assertTrue(false, () -> "makan (omnivora) gagal memvalidasi produk tanaman yang di cast ke produk sebagai makanan yang valid");
        }
    }
}