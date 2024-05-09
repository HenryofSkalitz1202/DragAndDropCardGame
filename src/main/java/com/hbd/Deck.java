package com.hbd;

import java.util.ArrayList;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;

public class Deck {
    private ArrayList<Kartu> deckKartu;

    public Deck() {
        this.deckKartu = new ArrayList<Kartu>();

        // buat 10 kartu Hewan, 10 kartu tanaman. 5 kartu ProdukHewan, 5 kartu
        // ProdukTanaman, 10 kartu item
        // ini contoh aja, kan takutnya gacha shufflenya jelek
        for (int i = 0; i < 10; i++) {
            ArrayList<String> data = FactoryKartu.dataHewan.get((int) (Math.random() * FactoryKartu.dataHewan.size()));
            String jenisHewan = data.get(0);
            String nama = data.get(1);
            int maksPanen = Integer.parseInt(data.get(2));
            String hasilPanen = data.get(3);
            Kartu kartu = FactoryKartu.makeKartuHewan(jenisHewan, nama, maksPanen, hasilPanen);

            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 10; i++) {
            ArrayList<String> data = FactoryKartu.dataTanaman
                    .get((int) (Math.random() * FactoryKartu.dataTanaman.size()));
            String nama = data.get(0);
            int maksPanen = Integer.parseInt(data.get(1));
            String hasilPanen = data.get(2);
            Kartu kartu = FactoryKartu.makeKartuTanaman(nama, maksPanen, hasilPanen);

            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 5; i++) {
            ArrayList<String> data = FactoryKartu.dataProdukHewan
                    .get((int) (Math.random() * FactoryKartu.dataProdukHewan.size()));
            String nama = data.get(0);
            int harga = Integer.parseInt(data.get(1));
            int tambahanBerat = Integer.parseInt(data.get(2));
            Kartu kartu = FactoryKartu.makeKartuProduk("ProdukHewan", nama, harga, tambahanBerat);

            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 5; i++) {
            ArrayList<String> data = FactoryKartu.dataProdukTanaman
                    .get((int) (Math.random() * FactoryKartu.dataProdukTanaman.size()));
            String nama = data.get(0);
            int harga = Integer.parseInt(data.get(1));
            int tambahanBerat = Integer.parseInt(data.get(2));
            Kartu kartu = FactoryKartu.makeKartuProduk("ProdukTanaman", nama, harga, tambahanBerat);

            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 10; i++) {
            String nama = FactoryKartu.dataItem.get((int) (Math.random() * FactoryKartu.dataItem.size()));
            Kartu kartu = FactoryKartu.makeKartuItem(nama);

            this.deckKartu.add(kartu);
        }
    }
}
