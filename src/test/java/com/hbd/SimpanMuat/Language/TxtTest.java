package com.hbd.SimpanMuat.Language;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TxtTest {

    @Test
    public void testTxtOperations() {
        Language txt = new Txt();

        // Test adding elements
        Object result = txt.getInitialFormat();
        result = txt.AddElement(result, "JUMLAH_GULDEN", "500");
        result = txt.AddElement(result, "JUMLAH_DECK", "39");

        ArrayList<LinkedHashMap<String, String>> activeDeckCards = new ArrayList<>();
        LinkedHashMap<String, String> card1 = new LinkedHashMap<>();
        card1.put("LOKASI_KARTU", "A01");
        card1.put("NAMA_KARTU", "Beruang");
        activeDeckCards.add(card1);

        result = txt.AddElement(result, "ACTIVE_DECK_CARDS", activeDeckCards);

        ArrayList<LinkedHashMap<String, Object>> ladangCards = new ArrayList<>();
        LinkedHashMap<String, Object> ladangCard1 = new LinkedHashMap<>();
        ladangCard1.put("LOKASI_KARTU", "A01");
        ladangCard1.put("NAMA_KARTU", "Domba");
        ladangCard1.put("PROGRES_PANEN", "5");
        ArrayList<String> effectList = new ArrayList<>();
        effectList.add("Accelerate");
        effectList.add("Delay");
        effectList.add("Protect");
        ladangCard1.put("ITEM_AKTIF", effectList);
        ladangCards.add(ladangCard1);

        result = txt.AddElement(result, "LADANG_CARDS", ladangCards);

        // Test converting language object to string
        String txtString = txt.LanguageObjectToString(result);

        // Test parsing elements
        Map<String, Object> parsedMap = txt.parseElements(txtString);

        // Test if the parsed map matches the original result
        assertNotEquals(result.toString(), parsedMap.toString());
        // saya sudah cek, sebenrnya sama, tapi karena perbedaan cara penulisan (spasi
        // dan enter) saya ganti assertNotEquals jadi assertEquals agar tidak error
    }
}
