package com.hbd.SimpanMuat.Language;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Yaml implements Language {

    @Override
    public Object AddElement(Object current, String key, Object value) {
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) current;
        result.put(key, value);
        return result;
    }

    @Override
    public Map<String, Object> parseElements(String scripture) {
        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
        return yaml.load(scripture);
    }

    public LinkedHashMap<String, Object> getInitialFormat() {
        return new LinkedHashMap<>();
    }

    @Override
    public String LanguageObjectToString(Object langObj) {
        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(langObj, writer);
        return "---\n" + writer;
    }

    @Override
    public String getExtension() {
        return "yaml";
    }
}
