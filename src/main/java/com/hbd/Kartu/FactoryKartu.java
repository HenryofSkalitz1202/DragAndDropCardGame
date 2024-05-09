package com.hbd.Kartu;

import com.hbd.Kartu.Makhluk.*;
import com.hbd.Kartu.Produk.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.hbd.Kartu.Item.*;

public class FactoryKartu {

    public static ArrayList<ArrayList<String>> dataHewan = new ArrayList<>() {
        {
            // jenis, nama, maksPanen, hasilPanen
            add(new ArrayList<>(Arrays.asList("Karnivora", "Hiu Darat", "20", "Sirip Hiu")));
            add(new ArrayList<>(Arrays.asList("Herbivora", "Kuda", "14", "Daging Kuda")));
            add(new ArrayList<>(Arrays.asList("Herbivora", "Domba", "12", "Daging Domba")));
            add(new ArrayList<>(Arrays.asList("Herbivora", "Sapi", "10", "Susu")));
            add(new ArrayList<>(Arrays.asList("Omnivora", "Ayam", "5", "Telur")));
            add(new ArrayList<>(Arrays.asList("Omnivora", "Beruang", "25", "Daging Beruang")));
        }
    };

    public static ArrayList<ArrayList<String>> dataTanaman = new ArrayList<>() {
        {
            // nama, maksPanen, hasilPanen
            add(new ArrayList<>(Arrays.asList("Biji Jagung", "3", "Jagung")));
            add(new ArrayList<>(Arrays.asList("Biji Labu", "5", "Labu")));
            add(new ArrayList<>(Arrays.asList("Biji Stroberi", "4", "Stroberi")));
        }
    };

    public static ArrayList<ArrayList<String>> dataProdukHewan = new ArrayList<>() {
        {
            // nama, harga, tambahanBerat
            add(new ArrayList<>(Arrays.asList("Sirip Hiu", "500", "12")));
            add(new ArrayList<>(Arrays.asList("Susu", "100", "4")));
            add(new ArrayList<>(Arrays.asList("Daging Domba", "120", "6")));
            add(new ArrayList<>(Arrays.asList("Daging Kuda", "150", "8")));
            add(new ArrayList<>(Arrays.asList("Telur", "50", "2")));
            add(new ArrayList<>(Arrays.asList("Daging Beruang", "500", "12")));
        }
    };

    public static ArrayList<ArrayList<String>> dataProdukTanaman = new ArrayList<>() {
        {
            add(new ArrayList<>(Arrays.asList("Jagung", "150", "3")));
            add(new ArrayList<>(Arrays.asList("Labu", "500", "10")));
            add(new ArrayList<>(Arrays.asList("Stroberi", "350", "5")));
        }
    };

    public static ArrayList<String> dataItem = new ArrayList<>() {
        {
            add("Accelerate");
            add("Delay");
            add("Instant Harvest");
            add("Destroy");
            add("Protect");
            add("Trap");
        }
    };

    public static Kartu makeKartuHewan(String jenisKartu, String nama, int maksPanen, String hasilPanen) {
        switch (jenisKartu) {
            case "Karnivora":
                return new Karnivora(nama, maksPanen, hasilPanen);
            case "Herbivora":
                return new Herbivora(nama, maksPanen, hasilPanen);
            case "Omnivora":
                return new Omnivora(nama, maksPanen, hasilPanen);
            default:
                return null;
        }
    }

    public static Kartu makeKartuTanaman(String nama, int maksPanen, String hasilPanen) {
        return new Tanaman(nama, maksPanen, hasilPanen);
    }

    public static Kartu makeKartuProduk(String jenisKartu, String nama, int harga, int tambahanBerat) {
        switch (jenisKartu) {
            case "ProdukHewan":
                return new ProdukHewan(nama, harga, tambahanBerat);
            case "ProdukTanaman":
                return new ProdukTanaman(nama, harga, tambahanBerat);
            default:
                return null;
        }
    }

    public static Kartu makeKartuItem(String nama) {
        return new Item(nama);
    }

    public static Kartu getKartu(String nama) {
        // cari nama di semua data
        for (ArrayList<String> data : dataHewan) {
            if (data.get(1).equals(nama)) {
                return makeKartuHewan(data.get(0), data.get(1), Integer.parseInt(data.get(2)), data.get(3));
            }
        }

        for (ArrayList<String> data : dataTanaman) {
            if (data.get(0).equals(nama)) {
                return makeKartuTanaman(data.get(0), Integer.parseInt(data.get(1)), data.get(2));
            }
        }

        for (ArrayList<String> data : dataProdukHewan) {
            if (data.get(0).equals(nama)) {
                return makeKartuProduk("ProdukHewan", data.get(0), Integer.parseInt(data.get(1)),
                        Integer.parseInt(data.get(2)));
            }
        }

        for (ArrayList<String> data : dataProdukTanaman) {
            if (data.get(0).equals(nama)) {
                return makeKartuProduk("ProdukTanaman", data.get(0), Integer.parseInt(data.get(1)),
                        Integer.parseInt(data.get(2)));
            }
        }

        for (String data : dataItem) {
            if (data.equals(nama)) {
                return makeKartuItem(data);
            }
        }

        return null;
    }
}
