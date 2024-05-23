package com.hbd.Deck;

import java.util.ArrayList;
import java.util.List;

import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;

public class Deck extends KartuContainer{

    public Deck() {
        super();
    }

    public Deck(List<Kartu> KartuKartu){
        super(KartuKartu);
    }

    public void addRandom(int N) throws DeckPenuhException {

        for (int i = 0; i < N; i++) {

            int kode = (int) (21.0 * Math.random());

            if (kode >= 0 && kode <= 7) {
                ArrayList<String> data = FactoryKartu.dataHewan.get((int) (Math.random() * FactoryKartu.dataHewan.size()));
                
                String jenisHewan = data.get(0);
                String nama = data.get(1);
                int maksPanen = Integer.parseInt(data.get(2));
                String hasilPanen = data.get(3);
                
                Kartu kartu = FactoryKartu.makeKartuHewan(jenisHewan, nama, maksPanen, hasilPanen);
                insertKartu(kartu);
            } else if (kode >= 7 && kode <= 14) {
                ArrayList<String> data = FactoryKartu.dataTanaman
                        .get((int) (Math.random() * FactoryKartu.dataTanaman.size()));
                String nama = data.get(0);
                int maksPanen = Integer.parseInt(data.get(1));
                String hasilPanen = data.get(2);
                Kartu kartu = FactoryKartu.makeKartuTanaman(nama, maksPanen, hasilPanen);

                insertKartu(kartu);
            } else if (kode >= 14 && kode <= 18) {
                String nama = FactoryKartu.dataItem.get((int) (Math.random() * FactoryKartu.dataItem.size()));
                Kartu kartu = FactoryKartu.makeKartuItem(nama);

                insertKartu(kartu);
            } else if (kode == 19){
                ArrayList<String> data = FactoryKartu.dataProdukHewan
                        .get((int) (Math.random() * FactoryKartu.dataProdukHewan.size()));
                String nama = data.get(0);
                int harga = Integer.parseInt(data.get(1));
                int tambahanBerat = Integer.parseInt(data.get(2));
                Kartu kartu = FactoryKartu.makeKartuProduk("ProdukHewan", nama, harga, tambahanBerat);

                insertKartu(kartu);
            } else if (kode >= 20) {
                ArrayList<String> data = FactoryKartu.dataProdukTanaman
                        .get((int) (Math.random() * FactoryKartu.dataProdukTanaman.size()));
                String nama = data.get(0);
                int harga = Integer.parseInt(data.get(1));
                int tambahanBerat = Integer.parseInt(data.get(2));
                Kartu kartu = FactoryKartu.makeKartuProduk("ProdukTanaman", nama, harga, tambahanBerat);

                insertKartu(kartu);
            }
        }
    }

    public void shuffle() throws Exception{
        for (int i = 0; i < size(); i++) {
            insertKartu(takeKartuAt((int) (Math.random() * size())));
        }
    }
}
