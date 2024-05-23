package com.hbd.Deck;

import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Herbivora;
import com.hbd.Kartu.Makhluk.Karnivora;
import com.hbd.Kartu.Makhluk.Omnivora;
import com.hbd.Kartu.Makhluk.Tanaman;
import com.hbd.Kartu.Produk.ProdukHewan;
import com.hbd.Kartu.Produk.ProdukTanaman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void insertKartuTest(){
        Deck testDeck = new Deck();

        try {
            testDeck.insertKartu(FactoryKartu.getKartu("Ayam"));
            testDeck.insertKartu(FactoryKartu.getKartu("Hiu Darat"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
        } catch(DeckPenuhException e){
            assertTrue(false, () -> "Deck memberikan exception penuh ketika di insert saat kosong");
        }

        try {
            Kartu testKartu = testDeck.peekTopKartu();
            assertTrue(testKartu.getNama().equals("Domba"), () -> "Deck gagal mendapatkan kartu paling atas sebagai kartu yang terakhir di insert");
            testKartu = testDeck.peekBottomKartu();
            assertTrue(testKartu.getNama().equals("Ayam"), () -> "Deck gagal mendapatkan kartu paling bawah sebagai kartu yang pertama di insert");
            testKartu = testDeck.peekKartuAt(1);
            assertTrue(testKartu.getNama().equals("Hiu Darat"), () -> "Deck gagal mendapatkan kartu dengan indeks 1 yaitu yang diinsert ke dua");
        } catch (DeckEmptyException | DeckOutOfBoundsException e) {
            assertTrue(false, () -> "Deck memberikan exception kosong ketika tidak kosong");
        }
    }

    @Test
    void takeKartuTest(){
        Deck testDeck = new Deck();

        try {
            testDeck.insertKartu(FactoryKartu.getKartu("Ayam"));
            testDeck.insertKartu(FactoryKartu.getKartu("Hiu Darat"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
        } catch (Exception e) {/* Diasumsikan Benar */}

        try {
            Kartu testKartu = testDeck.takeTopKartu();
            assertTrue(testKartu.getNama().equals("Domba"), () -> "Deck gagal mendapatkan kartu paling atas sebagai kartu yang terakhir di insert");
            assertTrue(testDeck.size() == 2, () -> "Deck gagal mengurangi size setelah kartu diambil");
        } catch (DeckEmptyException e) {
            assertTrue(false, () -> "Deck memberikan exception kosong ketika tidak kosong");
        }
    }

    @Test
    void shuffleTest(){
        Deck testDeck = new Deck();

        try {
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Domba"));
            testDeck.insertKartu(FactoryKartu.getKartu("Ayam"));
        } catch (Exception e) {/* Diasumsikan Benar */}

        try {
            testDeck.shuffle();
        } catch (Exception e) {/* Insha Allah tidak terjadi */}

        try {
            Kartu testKartu = testDeck.takeTopKartu();
            assertFalse(testKartu.getNama().equals("Ayam"), () -> "Shuffle gagal (atau anda sangat tidak beruntung)");
            assertTrue(testDeck.size() == 14, () -> "Shuffle somehow merubah size deck");
        } catch (Exception e) {/* Insha Allah tidak terjadi */}
    }

    @Test
    void addRandomTest(){
        Deck testDeck = new Deck();

        try {
            testDeck.addRandom(100);
        } catch (DeckPenuhException e){
            assertTrue(false, () -> "Deck dengan kapasitas tak hingga memberikan exception deck penuh");
        }

        assertTrue(testDeck.size() == 100, () -> "Deck gagal memiliki nilai size sama dengan jumlah yang telah di add");

        boolean HerbivoraExist = false, OmnivoraExist = false, KarnivoraExist = false, TanamanExist = false, ItemExist = false, ProdukHewanExist = false, ProdukTanamanExist = false;
        for(Kartu currCard : testDeck){
            if (currCard.getClass() == Herbivora.class){
                HerbivoraExist = true;
            } else if (currCard.getClass() == Omnivora.class) {
                OmnivoraExist = true;
            } else if (currCard.getClass() == Karnivora.class) {
                KarnivoraExist = true;
            } else if (currCard.getClass() == Tanaman.class) {
                TanamanExist = true;
            } else if (currCard.getClass() == Item.class) {
                ItemExist = true;
            } else if (currCard.getClass() == ProdukHewan.class) {
                ProdukHewanExist = true;
            } else if (currCard.getClass() == ProdukTanaman.class){
                ProdukTanamanExist = true;
            }
        }

        assertTrue(HerbivoraExist, () -> "Add Random Deck tidak menambah kelas Herbivora");
        assertTrue(OmnivoraExist, () -> "Add Random Deck tidak menambah kelas Omnivora");
        assertTrue(KarnivoraExist, () -> "Add Random Deck tidak menambah kelas Karnivora");
        assertTrue(TanamanExist, () -> "Add Random Deck tidak menambah kelas Tanaman");
        assertTrue(ItemExist, () -> "Add Random Deck tidak menambah kelas Item");
        assertTrue(ProdukHewanExist, () -> "Add Random Deck tidak menambah kelas ProdukHewan");
        assertTrue(ProdukTanamanExist, () -> "Add Random Deck tidak menambah kelas ProdukTanaman");
    }

    @Test
    void userDeckTest(){
        Deck userDeck = new Deck(6);

        try {
            userDeck.addRandom(6);
        } catch (DeckPenuhException e) {/* Diasumsikan tidak terjadi */}

        assertThrows(DeckPenuhException.class, () -> userDeck.addRandom(1), () -> "Deck User seharusnya throw exception ketika penuh dan berusaha untuk di insert");
        assertTrue(userDeck.size() == 6, () -> "Insertion yang gagal seharusnya tidak merubah deck");

        try{
            userDeck.takeTopKartu();
            assertTrue(userDeck.size() == 5, () -> "Setelah gagal insertion deck seharusnya tetap bisa dioperasikan");
            try{
                userDeck.insertKartu(FactoryKartu.getKartu("Ayam"));
            } catch (DeckPenuhException e){
                assertTrue(false, () -> "Setelah gagal insertion deck seharusnya tetap bisa dioperasikan");
            }
        } catch (Exception e) {/* Asumsikan tidak terjadi */}
    }

    @Test
    void deckExceptionTest(){
        Deck testDeck = new Deck();

        assertThrows(DeckEmptyException.class, () -> testDeck.takeTopKartu(), () -> "Deck seharusnya melempar exception ketika berusaha untuk mengambil kartu saat deck kosong");
        assertThrows(DeckEmptyException.class, () -> testDeck.takeBottomKartu(), () -> "Deck seharusnya melempar exception ketika berusaha untuk mengambil kartu saat deck kosong");
        assertThrows(DeckEmptyException.class, () -> testDeck.peekBottomKartu(), () -> "Deck seharusnya melempar exception ketika berusaha untuk mengambil kartu saat deck kosong");
        assertThrows(DeckEmptyException.class, () -> testDeck.peekTopKartu(), () -> "Deck seharusnya melempar exception ketika berusaha untuk mengambil kartu saat deck kosong");

        try {
            testDeck.addRandom(20);
        } catch (Exception e){
            assertTrue(false, () -> "Seharusnya tidak mungkin ke sini");
        }

        assertThrows(DeckOutOfBoundsException.class, () -> testDeck.takeKartuAt(100), "Deck seharusnya melempar exception ketika berusaha mengakses index yang terlalu tinggi");
        assertThrows(DeckOutOfBoundsException.class, () -> testDeck.peekKartuAt(-1), () -> "Deck seharusnya melempar exception ketika berusaha mengakses index negatif");
    }
}