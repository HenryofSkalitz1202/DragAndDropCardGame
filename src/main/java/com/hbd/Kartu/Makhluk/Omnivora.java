package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.Kartu.Produk.ProdukTanaman;

public class Omnivora extends Hewan {

    public Omnivora(String nama, int maksPanen, String hasilPanen) {
        super(nama, maksPanen, hasilPanen);
    }

    public void makan(Produk makanan) throws SalahMakanException {
        if (makanan.getClass() != ProdukHewan.class && makanan.getClass() != ProdukTanaman.class) {
            throw new SalahMakanException("Omnivora tidak bisa memakan itu");
        }
        setProgressPanen(getProgressPanen() + makanan.getTambahanBerat());
    }
}
