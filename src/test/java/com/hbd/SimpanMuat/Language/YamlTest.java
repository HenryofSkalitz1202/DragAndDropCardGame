package com.hbd.SimpanMuat.Language;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YamlTest {

    @Test
    public void testYamlOperations() {
        Language yaml = new com.hbd.SimpanMuat.Language.Yaml();

        // Test adding elements
        Object result = yaml.getInitialFormat();
        result = yaml.AddElement(result, "JUMLAH_GULDEN", "500");
        result = yaml.AddElement(result, "JUMLAH_DECK", "39");

        ArrayList<LinkedHashMap<String, String>> activeDeckCards = new ArrayList<>();
        LinkedHashMap<String, String> card1 = new LinkedHashMap<>();
        card1.put("LOKASI_KARTU", "A01");
        card1.put("NAMA_KARTU", "Beruang");
        activeDeckCards.add(card1);

        result = yaml.AddElement(result, "ACTIVE_DECK_CARDS", activeDeckCards);

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

        result = yaml.AddElement(result, "LADANG_CARDS", ladangCards);

        // Test converting language object to string
        String yamlString = yaml.LanguageObjectToString(result);

        // Test parsing elements
        Map<String, Object> parsedMap = yaml.parseElements(yamlString);

        // Test if the parsed map matches the original result
        assertEquals(result, parsedMap);
    }
}
