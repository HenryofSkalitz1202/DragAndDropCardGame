package com.hbd.Pemain;

import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Makhluk.Tanaman;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukTanaman;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.Toko.Toko;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PemainTest {

    private Pemain pemain;
    private Toko toko;
    private Tanaman tanaman;
    private Item item;
    private Produk produk;

    @BeforeEach
    public void setUp() {
        pemain = new Pemain();
        toko = Toko.getInstance();
    }

    @Test
    public void testGetDeck() {
        assertNotNull(pemain.getDeck());
    }

    @Test
    public void testGetDeckAktif() {
        assertNotNull(pemain.getDeckAktif());
    }

    @Test
    public void testGetPetakLadang() {
        assertNotNull(pemain.getPetakLadang());
    }

    @Test
    public void testGetDuit() {
        assertEquals(0, pemain.getDuit());
    }

    @Test
    public void testSetDuit() {
        pemain.setDuit(100);
        assertEquals(100, pemain.getDuit());
    }

    @Test
    public void testTambahDuit() {
        pemain.tambahDuit(50);
        assertEquals(50, pemain.getDuit());
    }

    @Test
    public void testPakaiDuit() {
        pemain.setDuit(100);
        pemain.pakaiDuit(30);
        assertEquals(70, pemain.getDuit());
    }

    @Test
    public void testBeriItemKeLadang() throws Exception {
        item = (Item) FactoryKartu.getKartu("Instant Harvest");
        tanaman = (Tanaman) FactoryKartu.getKartu("Biji Jagung");
        pemain.getPetakLadang().setMakhluk(0, 0, tanaman);
        pemain.beriItemKeLadang(pemain, item, 0, 0);

        assertEquals(tanaman.getMaksPanen(), tanaman.getProgressPanen());
    }

    @Test
    public void testPanenPetak() throws Exception {
        tanaman = (Tanaman) FactoryKartu.getKartu("Biji Jagung");
        tanaman.setProgressPanen(tanaman.getMaksPanen());
        pemain.getPetakLadang().setMakhluk(0, 0, tanaman);

        pemain.panenPetak(0, 0);

        assertNull(pemain.getPetakLadang().getMakhluk(0, 0));
        assertEquals(5, pemain.getDeckAktif().remainingSlot());
    }

    @Test
    public void testBeliProdukKeToko() throws Exception {
        produk = (ProdukTanaman) FactoryKartu.getKartu("Stroberi");
        toko.tambahItemKeToko(produk);
        pemain.setDuit(400);

        pemain.BeliProdukKeToko(toko, produk.getNama());

        assertEquals(50, pemain.getDuit());
        assertEquals(5, pemain.getDeckAktif().remainingSlot());
    }

    @Test
    public void testJualProdukKeToko() throws Exception {
        produk = (ProdukHewan) FactoryKartu.getKartu("Daging Kuda");
        pemain.getDeckAktif().insertKartu(produk);
        pemain.setDuit(400);
        toko.tambahItemKeToko(produk);

        pemain.JualProdukKeToko(toko, 0);

        assertEquals(550, pemain.getDuit());
        assertEquals(6, pemain.getDeckAktif().remainingSlot());
    }

    @Test
    public void testUpdateUmurPetak() throws DiluarPetakException {
        Tanaman tanaman = (Tanaman) FactoryKartu.getKartu("Biji Labu");
        pemain.getPetakLadang().setMakhluk(0, 0, (Makhluk) tanaman);

        pemain.updateUmurPetak();

        assertEquals(1, ((Tanaman) pemain.getPetakLadang().getMakhluk(0, 0)).getUmur());
    }
}
