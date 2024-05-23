package com.hbd.SimpanMuat.Language;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Txt implements Language{
    public StringBuilder AddElement(StringBuilder current , String key,  Object value){
        
        StringBuilder result = new StringBuilder(current);

        /**
         * If Trying To Add String
         */
        if (value.getClass() == String.class){
            if (result.length() != 0 && result.charAt(result.length() - 1) != '\n') {
                result.append('\n');
            }
            result.append(value);
        }
        else if (value.getClass() == ArrayList.class) {
            /**
             * if trying to add an array
             */
            if (((ArrayList<?>) value).get(0).getClass() == String.class) {
                /**
                 * If trying to add an array of string
                 */
                if (result.length() != 0 && result.charAt(result.length() - 1) != '\n' && result.charAt(result.length() - 1) != ' '){
                    result.append(' ');
                }
                result.append(((ArrayList<?>) value).size());
                for (Object str : (ArrayList<?>) value) {
                    if (result.length() != 0 && result.charAt(result.length() - 1) != '\n') {
                        result.append(' ');
                    }
                    result.append(str);
                }
            } else if (((ArrayList<?>) value).get(0).getClass() == LinkedHashMap.class) {
                /**
                 * If trying to add an array of hashmap
                 */
                if (result.length() != 0 && result.charAt(result.length() - 1) != '\n') {
                    result.append('\n');
                }
                result.append(((ArrayList<?>) value).size());

                for (Object map : ((ArrayList<?>) value)){
                    result = AddElement(result, "", map);
                }
            }
        } else if (value.getClass() == LinkedHashMap.class){
            /**
             * If trying to add a composite type
             */
            if (result.length() != 0 && result.charAt(result.length() - 1) != '\n') {
                result.append('\n');
            }
            boolean justStarted = true;
            for (Map.Entry<?, ?> entry : ((LinkedHashMap<?, ?>) value).entrySet()){
                if (!justStarted){
                    result.append(" ");
                } else {
                    justStarted = false;
                }
                if (entry.getValue().getClass() == String.class) {
                    result.append(entry.getValue());
                } else if (entry.getValue().getClass() == ArrayList.class) {
                    result = AddElement(result, (String) entry.getKey(), entry.getValue());
                }
            }
        }

        return result;
    }

    public static void main(String[] args){
        Txt bro = new Txt();

        StringBuilder result = new StringBuilder();
        result = bro.AddElement(result, "jumlah kon", "4");

        ArrayList<LinkedHashMap<String, String>> shopItem = new ArrayList<>();
        shopItem.add(new LinkedHashMap<>());
        shopItem.get(0).put("nama_item", "SIRIP_HIU");
        shopItem.get(0).put("jumlah_item", "3");
        shopItem.add(new LinkedHashMap<>());
        shopItem.get(1).put("nama_item", "JAGUNG");
        shopItem.get(1).put("jumlah_item", "7");
        shopItem.add(new LinkedHashMap<>());
        shopItem.get(2).put("nama_item", "DAGING_DOMBA");
        shopItem.get(2).put("jumlah_item", "9");
        result = bro.AddElement(result, "Toko", shopItem);

        result = bro.AddElement(result, "Jumlah Gulden", "100");
        result = bro.AddElement(result, "Jumlah Deck", "37");

        ArrayList<LinkedHashMap<String, Object>> ladangItem = new ArrayList<>();
        ladangItem.add(new LinkedHashMap<>());
        ladangItem.get(0).put("lokasi", "A01");
        ladangItem.get(0).put("nama_kartu", "domba");
        ladangItem.get(0).put("progress_panen", "5");
        ArrayList<String> effect = new ArrayList<>();
        effect.add("Accelerate");
        effect.add("Delay");
        effect.add("Protect");
        ladangItem.get(0).put("effect", effect);
        result = bro.AddElement(result, "Item Ladang", ladangItem);
        System.out.println(result);
    }
}
