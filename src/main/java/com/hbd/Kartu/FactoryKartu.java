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
            add(new ArrayList<>(Arrays.asList("Karnivora", "Hiu Darat", "img\\animal\\hiu_darat.jpg", "20", "Sirip Hiu")));
            add(new ArrayList<>(Arrays.asList("Herbivora", "Kuda", "img\\animal\\horse.jpg", "14", "Daging Kuda")));
            add(new ArrayList<>(Arrays.asList("Herbivora", "Domba", "img\\animal\\domba.jpg", "12", "Daging Domba")));
            add(new ArrayList<>(Arrays.asList("Herbivora", "Sapi", "img\\animal\\sapi.jpg", "10", "Susu")));
            add(new ArrayList<>(Arrays.asList("Omnivora", "Ayam", "img\\animal\\chicken.jpg", "5", "Telur")));
            add(new ArrayList<>(Arrays.asList("Omnivora", "Beruang", "img\\animal\\bear.jpg", "25", "Daging Beruang")));
        }
    };

    public static ArrayList<ArrayList<String>> dataTanaman = new ArrayList<>() {
        {
            // nama, maksPanen, hasilPanen
            add(new ArrayList<>(Arrays.asList("Biji Jagung", "img\\plant\\corn_kernel.jpg", "3", "Jagung")));
            add(new ArrayList<>(Arrays.asList("Biji Labu", "img\\plant\\pumpkin_seed.png", "5", "Labu")));
            add(new ArrayList<>(Arrays.asList("Biji Stroberi", "img\\plant\\strawberry_seed.jpg", "4", "Stroberi")));
        }
    };

    public static ArrayList<ArrayList<String>> dataProdukHewan = new ArrayList<>() {
        {
            // nama, harga, tambahanBerat
            add(new ArrayList<>(Arrays.asList("Sirip Hiu", "img\\productanimal\\shark_fin.jpg", "500", "12")));
            add(new ArrayList<>(Arrays.asList("Susu", "img\\productanimal\\milk.png", "100", "4")));
            add(new ArrayList<>(Arrays.asList("Daging Domba", "img\\productanimal\\mutton.jpg", "120", "6")));
            add(new ArrayList<>(Arrays.asList("Daging Kuda", "img\\productanimal\\horse_meat.png", "150", "8")));
            add(new ArrayList<>(Arrays.asList("Telur", "img\\productanimal\\egg.jpg", "50", "2")));
            add(new ArrayList<>(Arrays.asList("Daging Beruang", "img\\productanimal\\bear_meat.jpg", "500", "12")));
        }
    };

    public static ArrayList<ArrayList<String>> dataProdukTanaman = new ArrayList<>() {
        {
            add(new ArrayList<>(Arrays.asList("Jagung", "img\\productplant\\corn.jpg", "150", "3")));
            add(new ArrayList<>(Arrays.asList("Labu", "img\\productplant\\pumpkin.jpg", "500", "10")));
            add(new ArrayList<>(Arrays.asList("Stroberi", "img\\productplant\\strawberry.png", "350", "5")));
        }
    };

    public static ArrayList<ArrayList<String>> dataItem = new ArrayList<>() {
        {
            add(new ArrayList<>(Arrays.asList("Accelerate", "img\\item\\accelerate.png")));
            add(new ArrayList<>(Arrays.asList("Delay", "img\\item\\delay.jpg")));
            add(new ArrayList<>(Arrays.asList("Instant Harvest", "img\\item\\harvest.JPG")));
            add(new ArrayList<>(Arrays.asList("Destroy", "img\\item\\destroy.jpg")));
            add(new ArrayList<>(Arrays.asList("Protect", "img\\item\\protect.jpg")));
            add(new ArrayList<>(Arrays.asList("Trap", "img\\item\\trap.jpg")));
        }
    };

    public static Kartu makeKartuHewan(String jenisKartu, String nama, String imagePath, int maksPanen, String hasilPanen) {
        switch (jenisKartu) {
            case "Karnivora":
                return new Karnivora(nama, imagePath, maksPanen, hasilPanen);
            case "Herbivora":
                return new Herbivora(nama, imagePath, maksPanen, hasilPanen);
            case "Omnivora":
                return new Omnivora(nama, imagePath, maksPanen, hasilPanen);
            default:
                return null;
        }
    }

    public static Kartu makeKartuTanaman(String nama, String imagePath, int maksPanen, String hasilPanen) {
        return new Tanaman(nama, imagePath, maksPanen, hasilPanen);
    }

    public static Kartu makeKartuProduk(String jenisKartu, String nama, String imagePath, int harga, int tambahanBerat) {
        switch (jenisKartu) {
            case "ProdukHewan":
                return new ProdukHewan(nama, imagePath, harga, tambahanBerat);
            case "ProdukTanaman":
                return new ProdukTanaman(nama, imagePath, harga, tambahanBerat);
            default:
                return null;
        }
    }

    public static Kartu makeKartuItem(String nama, String imagePath) {
        return new Item(nama, imagePath);
    }

    public static Kartu getKartu(String nama) {
        // cari nama di semua data
        for (ArrayList<String> data : dataHewan) {
            if (data.get(1).equals(nama)) {
                return makeKartuHewan(data.get(0), data.get(1), data.get(2), Integer.parseInt(data.get(3)), data.get(4));
            }
        }

        for (ArrayList<String> data : dataTanaman) {
            if (data.get(0).equals(nama)) {
                return makeKartuTanaman(data.get(0), data.get(1), Integer.parseInt(data.get(2)), data.get(3));
            }
        }

        for (ArrayList<String> data : dataProdukHewan) {
            if (data.get(0).equals(nama)) {
                return makeKartuProduk("ProdukHewan", data.get(0), data.get(1), Integer.parseInt(data.get(2)),
                        Integer.parseInt(data.get(3)));
            }
        }

        for (ArrayList<String> data : dataProdukTanaman) {
            if (data.get(0).equals(nama)) {
                return makeKartuProduk("ProdukTanaman", data.get(0), data.get(1), Integer.parseInt(data.get(2)),
                        Integer.parseInt(data.get(3)));
            }
        }

        for (ArrayList<String> data : dataItem) {
            if (data.get(0).equals(nama)) {
                return makeKartuItem(data.get(0), data.get(1));
            }
        }

        return null;
    }
}
