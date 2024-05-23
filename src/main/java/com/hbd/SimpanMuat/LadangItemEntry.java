package com.hbd.SimpanMuat;

import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Makhluk.Makhluk;

import java.util.List;

public class LadangItemEntry {
    private String location;
    private Makhluk makhluk;
    private List<Item> items;

    public void LadangItemEntry(String _location, Makhluk _makhluk, List<Item> _items){
        location = _location;
        makhluk = _makhluk;
        items = _items;
    }

    public String getLocation() {
        return location;
    }

    public String getCardName(){
        return makhluk.getNama();
    }

    public Integer getPanenProgress(){
        return makhluk.getProgressPanen();
    }

    public Integer getJumlahItem(){
        return items.size();
    }

    public List<Item> getItems(){
        return items;
    }
}
