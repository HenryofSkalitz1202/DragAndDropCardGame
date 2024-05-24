package com.hbd.SimpanMuat.Language;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Json implements Language{

    // buat save state

    @Override
    public Object AddElement(Object current , String key,  Object value){

        JSONObject result = (JSONObject) current;

        result.put(key, value);
        return result;
    }

    public Map<String, Object> parseElements(String scripture) {
        JSONObject jsonObject = new JSONObject(scripture);
        return jsonObject.toMap();
    }

    public Object getInitialFormat(){
        return new JSONObject();
    }

    @Override
    public String LanguageObjectToString(Object langObj) {
        return langObj.toString();
    }

    @Override
    public String getExtension() {
        return "json";
    }

    //    public static void main(String[] args) {
//
//        for (int i = 0; i < 3; i++) {
//            Language bro;
//            if (i == 0){
//                bro = new Txt();
//            } else if (i == 1) {
//                bro = new Json();
//            } else {
//                bro = new Yaml();
//            }
//        StringBuilder result = new StringBuilder();
//        result = bro.AddElement(result, "CURRENT_TURN", "4");
//
//        ArrayList<LinkedHashMap<String, String>> shopItem = new ArrayList<>();
//        shopItem.add(new LinkedHashMap<>());
//        shopItem.get(0).put("NAMA_PRODUK", "SIRIP_HIU");
//        shopItem.get(0).put("JUMLAH_PRODUK", "3");
//        shopItem.add(new LinkedHashMap<>());
//        shopItem.get(1).put("NAMA_PRODUK", "JAGUNG");
//        shopItem.get(1).put("JUMLAH_PRODUK", "7");
//        shopItem.add(new LinkedHashMap<>());
//        shopItem.get(2).put("NAMA_PRODUK", "DAGING_DOMBA");
//        shopItem.get(2).put("JUMLAH_PRODUK", "9");
//
//        result = bro.AddElement(result, "SHOP_ITEMS", shopItem);
//        System.out.println(bro.parseElements(new String(result)));
//
//            Object result = bro.getInitialFormat();
//
//            result = bro.AddElement(result, "JUMLAH_GULDEN", "500");
//            result = bro.AddElement(result, "JUMLAH_DECK", "39");
//
//            ArrayList<LinkedHashMap<String, String>> active_deck_cards = new ArrayList<>();
//            active_deck_cards.add(new LinkedHashMap<>());
//            active_deck_cards.get(0).put("LOKASI_KARTU", "A01");
//            active_deck_cards.get(0).put("NAMA_KARTU", "Beruang");
//
//            result = bro.AddElement(result, "ACTIVE_DECK_CARDS", active_deck_cards);
//
//            ArrayList<LinkedHashMap<String, Object>> ladang_cards = new ArrayList<>();
//            ladang_cards.add(new LinkedHashMap<>());
//            ladang_cards.get(0).put("LOKASI_KARTU", "A01");
//            ladang_cards.get(0).put("NAMA_KARTU", "Domba");
//            ladang_cards.get(0).put("PROGRES_PANEN", "5");
//
//            ArrayList<String> effect_list = new ArrayList<String>();
//            effect_list.add("Accelerate");
//            effect_list.add("Delay");
//            effect_list.add("Protect");
//
//            ladang_cards.get(0).put("ITEM_AKTIF", effect_list);
//
//            result = bro.AddElement(result, "LADANG_CARDS", ladang_cards);
//
//            System.out.println(bro.LanguageObjectToString(result));
//            System.out.println();
//        }
//    }
}
