package com.hbd.Kartu.Item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemTest {

    @Test
    void ItemConstructorTest() {
        Item testItem = new Item("Accelerate");
        assertTrue(testItem.getNama().equals("Accelerate"), () -> "Kontruktor Item gagal set nama");
    }
}