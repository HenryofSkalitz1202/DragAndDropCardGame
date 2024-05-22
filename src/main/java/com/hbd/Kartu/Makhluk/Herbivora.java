package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.Kartu.Produk.ProdukTanaman;

public class Herbivora extends Hewan {

    public Herbivora(String nama, int maksPanen, String hasilPanen) {
        super(nama, maksPanen, hasilPanen);
    }

    public void makan(Produk makanan) throws SalahMakanException {
        if (makanan.getClass() != ProdukTanaman.class){
            throw new SalahMakanException("Herbivora tidak bisa memakan produk hewan");
        }
        setProgressPanen(getProgressPanen() + makanan.getTambahanBerat());
    }
}
