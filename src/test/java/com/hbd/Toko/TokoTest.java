package com.hbd.Toko;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.Kartu.Produk.ProdukTanaman;
import com.hbd.Toko.Exception.ProdukTidakDijualException;
import com.hbd.Toko.Exception.TokoHanyaMembeliProdukException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TokoTest {

    private Toko toko;

    @BeforeEach
    public void setUp() {
        Toko.resetInstance();
        toko = Toko.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        Toko anotherInstance = Toko.getInstance();
        assertSame(toko, anotherInstance);
    }

    @Test
    public void testBeliItemNotAvailable() {
        assertThrows(ProdukTidakDijualException.class, () -> {
            toko.beliItem("Sirip Hiu");
        });
    }

    @Test
    public void testJualItem() throws TokoHanyaMembeliProdukException {
        ProdukHewan produkHewan = (ProdukHewan) FactoryKartu.getKartu("Sirip Hiu");
        ProdukTanaman produkTanaman = (ProdukTanaman) FactoryKartu.getKartu("Jagung");

        int hargaHewan = toko.jualItem("Sirip Hiu");
        int hargaTanaman = toko.jualItem("Jagung");

        assertEquals(500, hargaHewan);
        assertEquals(150, hargaTanaman);

        HashMap<Produk, Integer> items = toko.getItemToko();
        assertTrue(items.containsKey(produkHewan));
        assertTrue(items.containsKey(produkTanaman));
    }

    @Test
    public void testJualItemNotProduk() {
        assertThrows(TokoHanyaMembeliProdukException.class, () -> {
            toko.jualItem("Biji Jagung");
        });
    }
}