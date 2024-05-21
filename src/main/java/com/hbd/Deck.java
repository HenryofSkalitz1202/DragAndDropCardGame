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
            Kartu kartu = FactoryKartu.getRandomHewan();
            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 10; i++) {
            Kartu kartu = FactoryKartu.getRandomTumbuhan();
            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 5; i++) {
            Kartu kartu = FactoryKartu.getRandomProdukHewan();
            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 5; i++) {
            Kartu kartu = FactoryKartu.getRandomProdukTumbuhan();
            this.deckKartu.add(kartu);
        }

        for (int i = 0; i < 10; i++) {
            Kartu kartu = FactoryKartu.getRandomItem();
            this.deckKartu.add(kartu);
        }
    }
}
