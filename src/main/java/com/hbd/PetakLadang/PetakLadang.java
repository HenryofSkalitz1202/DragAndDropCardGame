package com.hbd.PetakLadang;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;

public class PetakLadang implements Iterable<Makhluk> {

    private static final int lebar = 5;
    private static final int tinggi = 4;
    private final ArrayList<ArrayList<Makhluk>> ladang;

    /* Konstruktor PetakLadang
     * menginisialisasi petak ladang dengan berisi null
     */
    public PetakLadang() {
        this.ladang = new ArrayList<>();

        for (int i = 0; i < tinggi; i++) {
            ladang.add(new ArrayList<>());
            for (int j = 0; j < lebar; j++) {
                ladang.get(i).add(null);
            }
        }
    }

    /*
     * getMahkluk (int x, int y) --> mengembalikan makhluk yang ada pada koordinat x, y
     * @param
     * x : koordinat horizontal dengan range [0..lebar - 1], 0 adalah kolom yang paling kiri
     * y : koordinat vertikal dengan range [0..tinggi - 1], 0 adalah baris yang paling atas
     */
    public Makhluk getMakhluk(int x, int y) throws DiluarPetakException {
        if (x >= lebar || x < 0 || y >= tinggi || y < 0){
            throw new DiluarPetakException("Ada usaha untuk akses petak di luar ladang");
        }
        return this.ladang.get(y).get(x);
    }

    /*
     * getMahkluk (int x, int y, Makhluk makhluk) --> mengeset makhluk pada koordinat x, y
     * @param
     * x  : koordinat horizontal dengan range [0..lebar - 1], 0 adalah kolom yang paling kiri
     * y  : koordinat vertikal dengan range [0..tinggi - 1], 0 adalah baris yang paling atas
     * makhluk : makhluk yang ingin mengisi petak x, y
     */
    public void setMakhluk(int x, int y, Makhluk makhluk) throws DiluarPetakException {
        if (x >= lebar || x < 0 || y >= tinggi || y < 0){
            throw new DiluarPetakException("Ada usaha untuk akses petak di luar ladang");
        }
        this.ladang.get(y).set(x, makhluk);
    }

    public boolean hasAny(Method func) throws Exception{
        for(Makhluk makhluk : this){
            if ((boolean) func.invoke(makhluk)){
                return true;
            }
        }
        return false;
    }

    /* Diimplementasikan Iterator untuk PetakLadang */
    @Override
    public Iterator<Makhluk> iterator() {
        return new PetakLadangIterator();
    }

    class PetakLadangIterator implements Iterator<Makhluk>{
        private int baris = 0;
        private int kolom = 0;

        @Override
        public boolean hasNext() {
            return baris*lebar + kolom < lebar*tinggi;
        }

        @Override
        public Makhluk next() {
            int initialBaris = baris;
            int initialKolom = kolom;

            kolom++;
            if (kolom >= lebar){
                baris++;
                kolom -= lebar;
            }
            try {
                return getMakhluk(initialBaris, initialKolom);
            } catch (DiluarPetakException e){
                return null;
            }
        }
    }
}