package com.hbd.Kartu;

import com.hbd.Kartu.Makhluk.Exception.BelumSiapPanenException;
import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Makhluk.Karnivora;
import com.hbd.Kartu.Makhluk.Omnivora;
import com.hbd.Kartu.Makhluk.Tanaman;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.Kartu.Produk.ProdukTanaman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dependency : FactoryKartu, Kartu dan seluruh turunannya
 */

class PanenTest {

    @Test
    void panenHewanTest(){

        Omnivora ayam = (Omnivora) FactoryKartu.getKartu("Ayam");

        assertFalse(ayam.siapPanen(), () -> "Siap Panen gagal memberikan predikat bahwa ayam yang baru diinisialisasi tidak bisa dipanen");

        assertThrows(BelumSiapPanenException.class, () -> ayam.panen(), () -> "Panen gagal untuk throw exception ketika ayam belum siap dipanen");

        try {
            ayam.makan(new ProdukTanaman("Hello", 10, 100));
        } catch (SalahMakanException e) {;}

        try{
            Produk hasilPanen = ayam.panen();
            assertTrue(hasilPanen.getNama().equals("Telur"), () -> "Panen gagal memberikan hasil panen yang sesuai");
            assertTrue(hasilPanen.getClass() == ProdukHewan.class, () -> "Panen gagal untuk menjaga kelas produk tersebut");
        } catch (BelumSiapPanenException e){
            assertTrue(false, () -> "Panen membuat kesalahan throw exception meskipun sudah siap panen");
        }
    }

    @Test
    void panenTanamanTest(){

        Tanaman labu = (Tanaman) FactoryKartu.getKartu("Biji Labu");

        assertFalse(labu.siapPanen(), () -> "Siap Panen gagal memberikan predikat bahwa labu yang baru diinisialisasi tidak bisa dipanen");

        assertThrows(BelumSiapPanenException.class, () -> labu.panen(), () -> "Panen gagal untuk throw exception ketika labu belum siap dipanen");
        labu.setProgressPanen(50);

        try{
            Produk hasilPanen = labu.panen();
            assertTrue(hasilPanen.getNama().equals("Labu"), () -> "Panen gagal memberikan hasil panen yang sesuai");
            assertTrue(hasilPanen.getClass() == ProdukTanaman.class, () -> "Panen gagal untuk menjaga kelas produk tersebut");
        } catch (BelumSiapPanenException e){
            assertTrue(false, () -> "Panen membuat kesalahan throw exception meskipun sudah siap panen");
        }
    }
}