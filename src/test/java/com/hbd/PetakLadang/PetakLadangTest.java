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

    private PetakLadang petakLadang;

    @BeforeEach
    void setUp() {
        petakLadang = new PetakLadang();
    }

    @Test
    void testInitialLadang() {
        // Ensure all fields are initialized to null
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                try {
                    assertNull(petakLadang.getMakhluk(i, j), "Expected null at position (" + i + "," + j + ")");
                } catch (DiluarPetakException e) {
                    fail("Unexpected DiluarPetakException thrown");
                }
            }
        }
    }

    @Test
    void testSetMakhlukAndGetMakhluk() {
        Makhluk mockMakhluk = (Makhluk) FactoryKartu.getKartu("Ayam");

        try {
            petakLadang.setMakhluk(2, 3, mockMakhluk);
            assertEquals(mockMakhluk, petakLadang.getMakhluk(2, 3), "Expected Makhluk at position (2, 3)");
        } catch (DiluarPetakException e) {
            fail("Unexpected DiluarPetakException thrown");
        }
    }

    @Test
    void testSetMakhlukThrowsDiluarPetakException() {
        Makhluk mockMakhluk = (Makhluk) FactoryKartu.getKartu("Sapi");

        assertThrows(DiluarPetakException.class, () -> petakLadang.setMakhluk(-1, 0, mockMakhluk));
        assertThrows(DiluarPetakException.class, () -> petakLadang.setMakhluk(0, -1, mockMakhluk));
        assertThrows(DiluarPetakException.class, () -> petakLadang.setMakhluk(5, 0, mockMakhluk));
        assertThrows(DiluarPetakException.class, () -> petakLadang.setMakhluk(0, 5, mockMakhluk));
    }

    @Test
    void testGetMakhlukThrowsDiluarPetakException() {
        assertThrows(DiluarPetakException.class, () -> petakLadang.getMakhluk(-1, 0));
        assertThrows(DiluarPetakException.class, () -> petakLadang.getMakhluk(0, -1));
        assertThrows(DiluarPetakException.class, () -> petakLadang.getMakhluk(5, 0));
        assertThrows(DiluarPetakException.class, () -> petakLadang.getMakhluk(0, 5));
    }

    @Test
    void testIterator() {
        Iterator<Makhluk> iterator = petakLadang.iterator();
        int count = 0;

        while (iterator.hasNext()) {
            assertNull(iterator.next());
            count++;
        }

        assertEquals(25, count, "Expected iterator to iterate over 25 elements");
    }

    @Test
    void testHasAny() throws Exception {
        Makhluk mockMakhluk = (Makhluk) FactoryKartu.getKartu("Ayam");
        petakLadang.setMakhluk(0, 0, mockMakhluk);

        Method method = Makhluk.class.getMethod("siapPanen"); // Dummy method

        // Initially, the method should return false
        assertFalse(petakLadang.hasAny(method));

        // After adding a Makhluk, it should still return false as the method does not
        // match our mock implementation
        petakLadang.setMakhluk(2, 3, mockMakhluk);
        assertFalse(petakLadang.hasAny(method));
    }
}
