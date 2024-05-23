package com.hbd.Kartu;

import com.hbd.Kartu.Makhluk.Herbivora;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dependency : Item, Produk, Hewan, Tumbuhan, Herbivora, Karnivora, Omnivora
 */

class FactoryKartuTest {

    @Test
    void GetKartuTest(){
        Kartu currKartu = FactoryKartu.getKartu("Kuda");

        assertAll(
                () -> assertTrue(currKartu.getNama().equals("Kuda"), () -> "FactoryKartu gagal membuat kuda ketika diberikan naman Kuda"),
                () -> assertTrue(currKartu.getClass() == Herbivora.class, () -> "FactoryKartu gagal menjaga identitas kuda sebagai herbivora"),
                () -> assertTrue(((Herbivora)currKartu).getBeratPanen() == 14, () -> "FactoryKartu gagal membuat kuda dengan data berat panen sesuai spek"),
                () -> assertTrue(((Herbivora)currKartu).getHasilPanen().equals("Daging Kuda"), () -> "FactoryKartu gagal membuat kuda dengan data hasil panen sesuai spek")
        );
    }
}