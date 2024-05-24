package com.hbd.SimpanMuat.Language;

import java.util.*;

public class Txt implements Language{

    @Override
    public Object AddElement(Object current , String key,  Object value){

        StringBuilder result = new StringBuilder((StringBuilder) current);

        /**
         * If Trying To Add String
         */
        if (value.getClass() == String.class || value.getClass() == Integer.class){
            if (result.length() != 0 && result.charAt(result.length() - 1) != '\n') {
                result.append('\n');
            }
            result.append(((String) value).toUpperCase().replace(' ', '_'));
        }
        else if (value.getClass() == ArrayList.class) {

            /**
             * If empty just print zero
             */
            if (((ArrayList<?>) value).size() == 0) {
                if (result.length() != 0 && (result.charAt(result.length() - 1) != '\n') && result.charAt(result.length() - 1) != ' '){
                    result.append(' ');
                }
                result.append(((ArrayList<?>) value).size());
                return result;
            }

            /**
             * if trying to add an array
             */
            if (((ArrayList<?>) value).get(0).getClass() == String.class || ((ArrayList<?>) value).get(0).getClass() == Integer.class) {
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
                    result.append(((String) str).toUpperCase().replace(' ', '_'));
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
                    result = (StringBuilder) AddElement(result, "", map);
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
                    result.append(((String) (entry.getValue())).toUpperCase().replace(' ', '_'));
                } else if (entry.getValue().getClass() == ArrayList.class) {
                    result = (StringBuilder) AddElement(result, ((String) entry.getKey()).toUpperCase().replace(' ', '_'), entry.getValue());
                } else if (entry.getValue().getClass() == Integer.class){
                    result.append(entry.getValue());
                }
            }
        }

        return result;
    }

    @Override
    public Map<String, Object> parseElements(String scripture){
        Map<String, Object> result = new HashMap<String, Object>();

        List<String> eachLine = Arrays.asList(scripture.split("\n"));
        if (eachLine.size() < 3 || !Character.isDigit(eachLine.get(2).charAt(0))){
            /**
             * Adalah file gamestate, karena tidak memiliki line 3 atau
             * character pertama pada line 3 bukan sebuah angka (nama produk)
             */
            result.put("CURRENT_TURN", eachLine.get(0));
            result.put("SHOP_ITEMS", new ArrayList<LinkedHashMap<String, String>>());

            ArrayList<LinkedHashMap<String, String>> targetList = (ArrayList<LinkedHashMap<String, String>>) result.get("SHOP_ITEMS");

            int items_count = Integer.parseInt(eachLine.get(1));

            for(int i = 0; i < items_count; i++){
                List<String> ProductNameCount = Arrays.asList(eachLine.get(i + 2).split(" "));
                targetList.add(new LinkedHashMap<>());
                targetList.get(targetList.size() - 1).put("NAMA_PRODUK", ProductNameCount.get(0));
                targetList.get(targetList.size() - 1).put("JUMLAH_PRODUK", ProductNameCount.get(1));
            }
            return result;
        }
        else{
            /**
             * Adalah file player state (pasti memiliki line 3 atau
             * karakter pertama dari line 3 adalah digit)
             */
            result.put("JUMLAH_GULDEN", eachLine.get(0));
            result.put("JUMLAH_DECK", eachLine.get(1));

            result.put("ACTIVE_DECK_CARDS", new ArrayList<LinkedHashMap<String, String>>());

            ArrayList<LinkedHashMap<String, Object>> targetList = (ArrayList<LinkedHashMap<String, Object>>) result.get("ACTIVE_DECK_CARDS");
            int active_deck_cards_count = Integer.parseInt(eachLine.get(2));

            for (int i = 0; i < active_deck_cards_count; i++){
                List<String> CardLocationName = Arrays.asList(eachLine.get(i + 3).split(" "));
                targetList.add(new LinkedHashMap<>());
                targetList.get(targetList.size() - 1).put("LOKASI_KARTU", CardLocationName.get(0));
                targetList.get(targetList.size() - 1).put("NAMA_KARTU", CardLocationName.get(1));
            }

            result.put("LADANG_CARDS", new ArrayList<LinkedHashMap<String, String>>());
            targetList = (ArrayList<LinkedHashMap<String, Object>>) result.get("LADANG_CARDS");

            int ladang_card_count = Integer.parseInt(eachLine.get(3 + active_deck_cards_count));

            for (int i = 0; i < ladang_card_count; i++){
                List<String> LadangCardInfo = Arrays.asList(eachLine.get(i + active_deck_cards_count + 4).split(" "));
                targetList.add(new LinkedHashMap<>());
                targetList.get(targetList.size() - 1).put("LOKASI_KARTU", LadangCardInfo.get(0));
                targetList.get(targetList.size() - 1).put("NAMA_KARTU", LadangCardInfo.get(1));
                targetList.get(targetList.size() - 1).put("PROGRESS_PANEN", LadangCardInfo.get(2));

                int active_item_count = Integer.parseInt(LadangCardInfo.get(3));
                List<String> effectsList = new ArrayList<>();
                for(int j = 0; j < active_item_count; j++){
                    effectsList.add(LadangCardInfo.get(4 + j));
                }
                targetList.get(targetList.size() - 1).put("ITEM_AKTIF", effectsList);
            }
            return result;
        }
    }

    @Override
    public Object getInitialFormat(){ return new StringBuilder(); }

    @Override
    public String LanguageObjectToString(Object langObj) {
        return new String((StringBuilder) langObj);
    }

    @Override
    public String getExtension() {
        return "txt";
    }
}
