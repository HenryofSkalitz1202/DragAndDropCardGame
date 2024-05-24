package com.hbd.PetakLadang;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PetakLadangTest {
    @Test
    void testHasAny() throws Exception {
        PetakLadang petakLadang = new PetakLadang();
        Makhluk mockMakhluk = (Makhluk) FactoryKartu.getKartu("Ayam");
        petakLadang.setMakhluk(0, 0, mockMakhluk);

        Method method = Makhluk.class.getMethod("siapPanen"); // Dummy method

        assertFalse(petakLadang.hasAny(method));

        petakLadang.setMakhluk(2, 3, mockMakhluk);
        assertFalse(petakLadang.hasAny(method));
    }

    @Test
    void constructorTest() {
        PetakLadang testPetakLadang = new PetakLadang();

        int i = 0;
        for (Makhluk m : testPetakLadang) {
            assertTrue(m == null, () -> "PetakLadang gagal menginisialisasi diri sendiri dengan kosong");
            i++;
        }
        assertTrue(i == 20, () -> "PetakLadang diinisialisasi dengan ukuran 20");
    }

    @Test
    void exceptionTest() {
        PetakLadang testPetakLadang = new PetakLadang();

        try {
            assertTrue(testPetakLadang.getMakhluk(0, 0) == null,
                    () -> "PetakLadang gagal mendapatkan makhluk pada lokasi sesuai dengan argumen");
            testPetakLadang.setMakhluk(4, 3, (Makhluk) FactoryKartu.getKartu("Domba"));
            assertTrue(testPetakLadang.getMakhluk(4, 3).getNama().equals("Domba"),
                    () -> "PetakLadang gagal mendapat makhluk yang telah di set");
        } catch (DiluarPetakException e) {
            assertTrue(false, () -> "PetakLadang gagal memvalidasi petak yang valid");
        }

        assertThrows(DiluarPetakException.class, () -> testPetakLadang.getMakhluk(3, 4),
                () -> "PetakLadang gagal untuk throw exception ketika berusaha getMakhluk pada koordinat lebih besar dari ukuran petak");
        assertThrows(DiluarPetakException.class, () -> testPetakLadang.getMakhluk(-1, 2),
                () -> "PetakLadang gagal untuk throw exception ketika berusaha getMakhluk pada kolom negatif");
        assertThrows(DiluarPetakException.class,
                () -> testPetakLadang.setMakhluk(5, 2, (Makhluk) FactoryKartu.getKartu("Biji Labu")),
                () -> "PetakLadang gagal untuk throw exception ketika berusaha setMakhluk pada koordinat lebih besar dari ukuran petak");
        assertThrows(DiluarPetakException.class,
                () -> testPetakLadang.setMakhluk(4, -1, (Makhluk) FactoryKartu.getKartu("Biji Jagung")),
                () -> "PetakLadang gagal untuk throw exception ketika berusaha setMakhluk pada baris negatif");

    }
}
