package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;

public class Omnivora extends Hewan {

    public Omnivora(String nama, int maksPanen, String hasilPanen) {
        super(nama, maksPanen, hasilPanen);
    }

    public void makan(Produk makanan) throws SalahMakanException {
        setProgressPanen(getProgressPanen() + makanan.getTambahanBerat());
    }
}
