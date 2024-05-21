package com.hbd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;

/**
 * Kelas Toko (Singleton)
 * 
 * Cara menggunakan kelas ini:
 * 1. Panggil getInstance() untuk membuat instance Toko
 * 2. Panggil getInstance(String pathFileGameState) untuk membuat instance Toko
 * dengan path file gamestate.txt
 * 3. Panggil resetInstance() untuk menghilangkan instance Toko yang ada saat
 * ini
 * 
 */
public class Toko {
    private static Toko instance;
    private HashMap<Kartu, Integer> mapItem;

    private Toko() {
        this.mapItem = new HashMap<Kartu, Integer>();
    }

    private Toko(String pathFileGameState) {
        this.mapItem = new HashMap<Kartu, Integer>();
        initTokoFromGameState(pathFileGameState);
    }

    // Membuat instance Toko
    public static synchronized Toko getInstance() {
        if (instance == null) {
            instance = new Toko();
        }
        return instance;
    }

    // Membuat instance Toko dengan path file gamestate.txt
    public static synchronized Toko getInstance(String pathFileGameState) {
        if (instance == null) {
            instance = new Toko(pathFileGameState);
        }
        return instance;
    }

    // Menhilangkan instance Toko yang ada saat ini
    public static synchronized void resetInstance() {
        instance = null;
    }

    // inisiasi toko dari gamestate.txt
    private void initTokoFromGameState(String pathFileGameState) {

        try {
            File myObj = new File(pathFileGameState);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); // skip line 1 (turn saat ini)
            int banyakItemDiToko = Integer.parseInt(myReader.nextLine());

            for (int i = 0; i < banyakItemDiToko; i++) {
                String dataItem = myReader.nextLine();
                String[] dataItemSplit = dataItem.split(" ");
                Kartu item = FactoryKartu.getKartu(dataItemSplit[0]);
                int banyakItem = Integer.parseInt(dataItemSplit[1]);
                for (int j = 0; j < banyakItem; j++) {
                    tambahItemKeToko(item);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saat baca gamestate.txt.");
            e.printStackTrace();
        }
    }

    public void tambahItemKeToko(Kartu item) {
        if (mapItem.containsKey(item)) {
            mapItem.put(item, mapItem.get(item) + 1);
        } else {
            mapItem.put(item, 1);
        }
    }

    public HashMap<Kartu, Integer> getMapItem() {
        return this.mapItem;
    }

    public Kartu beliItem(String namaItem) {
        Kartu item = FactoryKartu.getKartu(namaItem);
        if (mapItem.containsKey(item)) {
            mapItem.put(item, mapItem.get(item) - 1);
            if (mapItem.get(item) == 0) {
                mapItem.remove(item);
            }
        } else {
            item = null;
        }

        return item;
    }
}
