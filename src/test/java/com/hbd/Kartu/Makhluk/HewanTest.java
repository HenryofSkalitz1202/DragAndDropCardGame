package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Makhluk.Exception.ItemTidakAdaException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dependency : Item
 */

class HewanTest {

    static Hewan ihsan;

    @BeforeEach
    void setup(){
        ihsan = new Herbivora("Ihsan", 10, "TelurIhsan");
    }

    @Test
    void ConstructorTest() {
        assertAll(
                () -> assertTrue(ihsan.getNama().equals("Ihsan"), () -> "Konstruktor Hewan gagal set nama hewan"),
                () -> assertTrue(ihsan.getBeratPanen() == 10, () -> "Konstruktor Hewan gagal set berat panen"),
                () -> assertTrue(ihsan.getHasilPanen().equals("TelurIhsan"), () -> "Konstruktor Hewan gagal set hasilPanen")
        );
    }

    @Test
    void tambahBerat() {
        ihsan.tambahBerat(5);
        assertTrue(ihsan.getBerat() == 5, () -> "TambahBerat hewan gagal menambah berat");
    }

    @Test
    void cekBeratPanen() {

        ihsan.tambahBerat(8);
        assertTrue(!ihsan.siapPanen(), () -> "siapPanen gagal memberikan false ketika hewan belum siap panen");

        ihsan.tambahBerat(2);
        assertTrue(ihsan.siapPanen(), () -> "siapPanen gagal memberikan true ketika hewan tepat siap panen");

        ihsan.tambahBerat(3);
        assertTrue(ihsan.siapPanen(), () -> "siapPanen gagal memberikan true ketika berat hewan lebih dari berat panen");
    }

    /* Method Panen tidak dites di sini karena menggunakan FactoryKartu */

    @Test
    void HisabItem(){

        ihsan.tambahBerat(2);

        try {
            ihsan.hisabItem(new Item("Accelerate"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem throw Exception gagal memvalidasi item Accelerate sebagai item yang valid");}

        assertTrue(ihsan.getBerat() == 10, () -> "hisabItem gagal menambah berat hewan sebanyak 8");
        assertTrue(ihsan.getEffect().get(0).equals("Accelerate"), () -> "hisabItem gagal mencatat accelerate ke list effect setelah diberikan");

        try {
            ihsan.hisabItem(new Item("Delay"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem gagal memvalidasi item Delay sebagai item yang valid");}

        assertTrue(ihsan.getBerat() == 5, () -> "hisabItem gagal mengurangi berat hewan sebanyak 5");
        assertTrue(ihsan.getEffect().get(1).equals("Delay"), () -> "hisabItem gagal mencatat delay ke list effect setelah diberikan");

        try {
            ihsan.hisabItem(new Item("Protect"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem gagal memvalidasi item Protect sebagai item yang valid");}

        assertTrue(ihsan.getEffect().get(2).equals("Protect"), () -> "hisabItem gagal mencatat Protect ke list effect setelah diberikan");

        try {
            ihsan.hisabItem(new Item("Trap"));
        } catch (Exception e) {assertTrue(false, () -> "hisabItem gagal memvalidasi item Trap sebagai item yang valid");}

        assertTrue(ihsan.getEffect().get(3).equals("Trap"), () -> "hisabItem gagal mencatat Trap ke list effect setelah diberikan");

        assertThrows(ItemTidakAdaException.class, () -> ihsan.hisabItem(new Item ("Ellijah Darrellshane")), () -> "Hewan gagal memberikan exception ketika menghisab item yang tidak ada");

    }
}