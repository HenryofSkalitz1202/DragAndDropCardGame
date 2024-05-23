package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Makhluk.Exception.ItemTidakAdaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dependency : Item
 */

class TanamanTest {
    
    static Tanaman kaktus;

    @BeforeEach
    void setup(){
        kaktus = new Tanaman("Bonbon", 3, "Duri");
    }

    @Test
    void ConstructorTest() {
        assertAll(
                () -> assertTrue(kaktus.getNama().equals("Bonbon"), () -> "Konstruktor Tanaman gagal set nama tanaman"),
                () -> assertTrue(kaktus.getUmurPanen() == 3, () -> "Konstruktor Tanaman gagal set umur panen"),
                () -> assertTrue(kaktus.getHasilPanen().equals("Duri"), () -> "Konstruktor Tanaman gagal set hasilPanen")
        );
    }

    @Test
    void tambahUmur() {
        kaktus.tambahUmurSatu();
        assertTrue(kaktus.getUmur() == 1, () -> "tambahUmurSatu tanaman gagal menambah umur");
    }

    @Test
    void cekUmurPanen() {

        kaktus.tambahUmurSatu();
        assertTrue(!kaktus.siapPanen(), () -> "siapPanen gagal memberikan false ketika tanaman belum siap panen");

        kaktus.tambahUmurSatu();
        kaktus.tambahUmurSatu();
        assertTrue(kaktus.siapPanen(), () -> "siapPanen gagal memberikan true ketika tanaman tepat siap panen");

        kaktus.tambahUmurSatu();
        kaktus.tambahUmurSatu();
        assertTrue(kaktus.siapPanen(), () -> "siapPanen gagal memberikan true ketika umur tanaman lebih dari umur panen");
    }

    /* Method Panen tidak dites di sini karena menggunakan FactoryKartu */

    @Test
    void HisabItem(){

        kaktus.tambahUmurSatu();

        try {
            kaktus.hisabItem(new Item("Accelerate"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem throw Exception gagal memvalidasi item Accelerate sebagai item yang valid");}

        assertTrue(kaktus.getUmur() == 3, () -> "hisabItem gagal menambah umur tanaman sebanyak 8");
        assertTrue(kaktus.getEffect().get(0).equals("Accelerate"), () -> "hisabItem gagal mencatat accelerate ke list effect setelah diberikan");

        try {
            kaktus.hisabItem(new Item("Delay"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem gagal memvalidasi item Delay sebagai item yang valid");}

        assertTrue(kaktus.getUmur() == 1, () -> "hisabItem gagal mengurangi umur tanaman sebanyak 5");
        assertTrue(kaktus.getEffect().get(1).equals("Delay"), () -> "hisabItem gagal mencatat delay ke list effect setelah diberikan");

        try {
            kaktus.hisabItem(new Item("Protect"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem gagal memvalidasi item Protect sebagai item yang valid");}

        assertTrue(kaktus.getEffect().get(2).equals("Protect"), () -> "hisabItem gagal mencatat Protect ke list effect setelah diberikan");

        try {
            kaktus.hisabItem(new Item("Trap"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem gagal memvalidasi item Trap sebagai item yang valid");}

        assertTrue(kaktus.getEffect().get(3).equals("Trap"), () -> "hisabItem gagal mencatat Trap ke list effect setelah diberikan");

        assertThrows(ItemTidakAdaException.class, () -> kaktus.hisabItem(new Item ("Ellijah Darrelshane")));

    }
}