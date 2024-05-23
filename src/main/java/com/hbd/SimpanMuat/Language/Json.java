package com.hbd.SimpanMuat.Language;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Json {

    public JSONObject AddSingularElement(JSONObject current, String key, String value) {
        current.put(key, value);
        return current;
    }

    public JSONObject AddListOfElement(JSONObject current, String key, List<String> elements) {
        current.put(key, elements);
        return current;
    }

    public JSONObject AddCompositeElement(JSONObject current, String key, List<String> keys, List<String> values) {
        JSONObject composite = new JSONObject();
        for (int i = 0; i < keys.size(); i++) {
            composite.put(keys.get(i), values.get(i));
        }
        current.put(key, composite);
        return current;
    }

    public JSONObject getInitialFormat() {
        return new JSONObject();
    }

    public static void writeJson(JSONObject jsonObject) {
        try {
            FileWriter file = new FileWriter("output.json");
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // try {
    // FileWriter file = new FileWriter("output.json");
    // file.write(jsonObject.toJSONString());
    // file.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

}
