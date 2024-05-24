package com.hbd.Kartu;

import com.hbd.Kartu.Exception.CardNotFoundException;
import com.hbd.Kartu.Exception.JenisKartuTidakValidException;
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
    
    public static Kartu makeKartuHewan(String jenisKartu, String nama, int maksPanen, String hasilPanen) throws JenisKartuTidakValidException {
        switch (jenisKartu) {
            case "Karnivora":
                return new Karnivora(nama, maksPanen, hasilPanen);
            case "Herbivora":
                return new Herbivora(nama, maksPanen, hasilPanen);
            case "Omnivora":
                return new Omnivora(nama, maksPanen, hasilPanen);
            default:
                throw new JenisKartuTidakValidException("Jenis Kartu " + jenisKartu + " tidak valid");
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

    public static Kartu getKartu(String nama) throws CardNotFoundException, JenisKartuTidakValidException {
        // cari nama di semua data
        nama = nama.replace('_', ' ');
        for (ArrayList<String> data : dataHewan) {
            if (data.get(1).equalsIgnoreCase(nama)) {
                return makeKartuHewan(data.get(0), data.get(1), Integer.parseInt(data.get(2)), data.get(3));
            }
        }

        for (ArrayList<String> data : dataTanaman) {
            if (data.get(0).equalsIgnoreCase(nama)) {
                return makeKartuTanaman(data.get(0), Integer.parseInt(data.get(1)), data.get(2));
            }
        }

        for (ArrayList<String> data : dataProdukHewan) {
            if (data.get(0).equalsIgnoreCase(nama)) {
                return makeKartuProduk("ProdukHewan", data.get(0), Integer.parseInt(data.get(1)),
                        Integer.parseInt(data.get(2)));
            }
        }

        for (ArrayList<String> data : dataProdukTanaman) {
            if (data.get(0).equalsIgnoreCase(nama)) {
                return makeKartuProduk("ProdukTanaman", data.get(0), Integer.parseInt(data.get(1)),
                        Integer.parseInt(data.get(2)));
            }
        }

        for (String data : dataItem) {
            if (data.equalsIgnoreCase(nama)) {
                return makeKartuItem(data);
            }
        }

        throw new CardNotFoundException("Kartu dengan nama " + nama + " tidak ditemukan");
    }

    public static Kartu getRandomHewan() throws JenisKartuTidakValidException {
        ArrayList<String> data = FactoryKartu.dataHewan.get((int) (Math.random() * FactoryKartu.dataHewan.size()));
        String jenisHewan = data.get(0);
        String nama = data.get(1);
        int maksPanen = Integer.parseInt(data.get(2));
        String hasilPanen = data.get(3);
        Kartu kartu = FactoryKartu.makeKartuHewan(jenisHewan, nama, maksPanen, hasilPanen);

        return kartu;
    }

    public static Kartu getRandomTumbuhan(){
        ArrayList<String> data = FactoryKartu.dataTanaman.get((int) (Math.random() * FactoryKartu.dataTanaman.size()));
        String nama = data.get(0);
        int maksPanen = Integer.parseInt(data.get(1));
        String hasilPanen = data.get(2);
        Kartu kartu = FactoryKartu.makeKartuTanaman(nama, maksPanen, hasilPanen);

        return kartu;
    }

    public static Kartu getRandomProdukHewan(){
        ArrayList<String> data = FactoryKartu.dataProdukHewan.get((int) (Math.random() * FactoryKartu.dataProdukHewan.size()));
        String nama = data.get(0);
        int harga = Integer.parseInt(data.get(1));
        int tambahanBerat = Integer.parseInt(data.get(2));
        Kartu kartu = FactoryKartu.makeKartuProduk("ProdukHewan", nama, harga, tambahanBerat);

        return kartu;
    }

    public static Kartu getRandomProdukTumbuhan(){
        ArrayList<String> data = FactoryKartu.dataProdukTanaman.get((int) (Math.random() * FactoryKartu.dataProdukTanaman.size()));
        String nama = data.get(0);
        int harga = Integer.parseInt(data.get(1));
        int tambahanBerat = Integer.parseInt(data.get(2));
        Kartu kartu = FactoryKartu.makeKartuProduk("ProdukTanaman", nama, harga, tambahanBerat);

        return kartu;
    }

    public static Kartu getRandomItem(){
        String data = FactoryKartu.dataItem.get((int) (Math.random() * FactoryKartu.dataItem.size()));
        Kartu kartu = FactoryKartu.makeKartuItem(data);

        return kartu;
    }
}
