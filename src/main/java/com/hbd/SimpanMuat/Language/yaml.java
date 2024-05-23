package com.hbd.SimpanMuat.Language;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class yaml {

    public LinkedHashMap<String, Object> AddSingularElement(LinkedHashMap<String, Object> current, String key,
            String value) {
        current.put(key, value);
        return current;
    }

    public LinkedHashMap<String, Object> AddListOfElement(LinkedHashMap<String, Object> current, String key,
            String[] elements) {
        current.put(key, elements);
        return current;
    }

    public LinkedHashMap<String, Object> AddCompositeElement(LinkedHashMap<String, Object> current, String key,
            String[] keys, String[] values) {
        LinkedHashMap<String, Object> composite = new LinkedHashMap<>();
        for (int i = 0; i < keys.length; i++) {
            composite.put(keys[i], values[i]);
        }
        current.put(key, composite);
        return current;
    }

    public LinkedHashMap<String, Object> getInitialFormat() {
        return new LinkedHashMap<>();
    }

    public static void writeYaml(LinkedHashMap<String, Object> data) {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter("output.yaml")) {
            yaml.dump(data, writer); // data bakal ke write ke output.yaml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Yaml yaml = new Yaml();
    // FileWriter writer = new FileWriter("output.yaml");
    // yaml.dump(data, writer); // data bakal ke write ke output.yaml
}
