package com.hbd.SimpanMuat.Language;

import java.util.List;

public interface Language {
    StringBuilder AddSingularElement(StringBuilder current , String key, String value);
    StringBuilder AddListOfElement(StringBuilder current , String key, List<String> elements);
    StringBuilder AddCompositeElement(StringBuilder current , String key, List<String> keys, List<String> values);
    StringBuilder getInitialFormat();
}
