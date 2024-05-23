package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Makhluk.Exception.SalahMakanException;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Kartu.Produk.ProdukHewan;

public class Karnivora extends Hewan {

    public Karnivora(String nama, int maksPanen, String hasilPanen) {
        super(nama, maksPanen, hasilPanen);
    }

    public void makan(Produk makanan) throws SalahMakanException{
        if (makanan.getClass() != ProdukHewan.class){
            throw new SalahMakanException("Karnivora tidak bisa memakan produk tumbuhan");
        }
        setProgressPanen(getProgressPanen() + makanan.getTambahanBerat());
    }
}
