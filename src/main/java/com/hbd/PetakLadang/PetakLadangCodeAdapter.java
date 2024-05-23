package com.hbd.PetakLadang;

import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import javafx.util.Pair;

public class PetakLadangCodeAdapter {

    private PetakLadang petakLadang;

    public PetakLadangCodeAdapter(){
        petakLadang = new PetakLadang();
    }

    public PetakLadang getPetakLadang() {
        return petakLadang;
    }

    public Makhluk getMakhluk(String code) throws DiluarPetakException {
        Pair<Integer, Integer> koordinat = codeParser(code);
        return petakLadang.getMakhluk(koordinat.getKey(), koordinat.getValue());
    }

    public void setMakhluk(String code, Makhluk makhluk) throws DiluarPetakException{
        Pair<Integer, Integer> koordinat = codeParser(code);
        petakLadang.setMakhluk(koordinat.getKey(), koordinat.getValue(), makhluk);
    }

    private Pair<Integer, Integer> codeParser(String code){
        char letterCode = code.charAt(0);
        int baris = letterCode - 'A';
        int kolom = Integer.parseInt(code.substring(1));
        return new Pair<>(baris, kolom);
    }
}
