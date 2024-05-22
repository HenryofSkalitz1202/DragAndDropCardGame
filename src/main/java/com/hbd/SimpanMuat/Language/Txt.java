package com.hbd.SimpanMuat.Language;

import java.util.List;

public class Txt implements Language{

    @Override
    public StringBuilder AddSingularElement(StringBuilder current, String key, String value){
        return new StringBuilder(current + "\n" + value);
    }

    @Override
    public StringBuilder AddListOfElement(StringBuilder current, String key, List<String> elements){
        StringBuilder result = new StringBuilder(current);
        result.append("\n");
        result.append(String.valueOf(elements.size()));
        for(String entry : elements){
            result.append('\n');
            result.append(entry);
        }
        return result;
    }

    @Override
    public StringBuilder AddCompositeElement(StringBuilder current, String key, List<String> keys, List<String> values){
        StringBuilder result = new StringBuilder(current);
        for (String element : values){
            result.append(' ');
            result.append(element);
        }
        return result;
    }

    @Override
    public StringBuilder getInitialFormat(){
        return new StringBuilder();
    }
}
