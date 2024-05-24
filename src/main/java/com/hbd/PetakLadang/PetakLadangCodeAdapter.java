package com.hbd.PetakLadang;

import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import javafx.util.Pair;

import java.util.Iterator;

public class PetakLadangCodeAdapter implements Iterable<Makhluk> {

    private PetakLadang petakLadang;

    public PetakLadangCodeAdapter(){
        petakLadang = new PetakLadang();
    }
    public PetakLadangCodeAdapter(PetakLadang _petakLadang) {petakLadang = _petakLadang;}

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
        int baris = Integer.parseInt(code.substring(1));
        int kolom = letterCode - 'A';
        return new Pair<>(baris - 1, kolom);
    }

    /* Diimplementasikan Iterator untuk PetakLadang */
    @Override
    public Iterator<Makhluk> iterator() {
        return new PetakLadangCodeAdapterIterator();
    }

    public class PetakLadangCodeAdapterIterator implements Iterator<Makhluk> {
        private char baris = 'A';
        private int kolom = 1;

        @Override
        public boolean hasNext() {
            return baris - 'A' < petakLadang.getTinggi() && kolom - 1 < petakLadang.getLebar();
        }

        @Override
        public Makhluk next() {
            char initialBaris = baris;
            int initialKolom = kolom;

            kolom++;
            if (kolom - 1 >= petakLadang.getLebar()) {
                baris++;
                kolom -= petakLadang.getLebar();
            }
            try {
                return getMakhluk(initialBaris + "0" + initialKolom);
            } catch (DiluarPetakException e) {
                return null;
            }
        }

        public String getCurrentCoordinates(){
            return baris + "0" + kolom;
        }
    }
}
