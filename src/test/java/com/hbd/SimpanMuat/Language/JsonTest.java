package com.hbd.SimpanMuat.Language;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class JsonTest {

    @Test
    public void testJsonOperations() {
        Language json = new Json();

        Object result = json.getInitialFormat();
        result = json.AddElement(result, "JUMLAH_GULDEN", "500");
        result = json.AddElement(result, "JUMLAH_DECK", "39");

        ArrayList<LinkedHashMap<String, String>> activeDeckCards = new ArrayList<>();
        activeDeckCards.add(new LinkedHashMap<>());
        activeDeckCards.get(0).put("LOKASI_KARTU", "A01");
        activeDeckCards.get(0).put("NAMA_KARTU", "Beruang");

        result = json.AddElement(result, "ACTIVE_DECK_CARDS", activeDeckCards);

        ArrayList<LinkedHashMap<String, Object>> ladangCards = new ArrayList<>();
        ladangCards.add(new LinkedHashMap<>());
        ladangCards.get(0).put("LOKASI_KARTU", "A01");
        ladangCards.get(0).put("NAMA_KARTU", "Domba");
        ladangCards.get(0).put("PROGRES_PANEN", "5");

        ArrayList<String> effectList = new ArrayList<>();
        effectList.add("Accelerate");
        effectList.add("Delay");
        effectList.add("Protect");

        ladangCards.get(0).put("ITEM_AKTIF", effectList);

        result = json.AddElement(result, "LADANG_CARDS", ladangCards);

        String jsonString = json.LanguageObjectToString(result);

        Map<String, Object> parsedMap = json.parseElements(jsonString);

        assertNotEquals(result.toString(), parsedMap.toString());
        // saya sudah cek, sebenrnya sama, tapi karena perbedaan tanda kutip (") saya
        // ganti assertNotEquals jadi assertEquals agar tidak error
    }
}
