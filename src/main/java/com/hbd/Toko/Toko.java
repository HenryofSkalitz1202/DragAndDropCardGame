
package com.hbd.Toko;

import java.util.HashMap;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Pemain.Pemain;
import com.hbd.Toko.Exception.ProdukTidakDijualException;
import com.hbd.Toko.Exception.TokoHanyaMembeliProdukException;

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
    private HashMap<Produk, Integer> itemToko;

    private Toko() {
        this.itemToko = new HashMap<Produk, Integer>();
    }

    // Membuat instance Toko
    public static synchronized Toko getInstance() {
        if (instance == null) {
            instance = new Toko();
        }
        return instance;
    }

    // Menhilangkan instance Toko yang ada saat ini
    public static synchronized void resetInstance() {
        instance = null;
    }

    public void tambahItemKeToko(Produk item) {
        if (itemToko.containsKey(item)) {
            itemToko.put(item, itemToko.get(item) + 1);
        } else {
            itemToko.put(item, 1);
        }
    }

    public HashMap<Produk, Integer> getItemToko() {
        return this.itemToko;
    }

    public Produk beliItem(String namaItem) throws ProdukTidakDijualException{
        Produk item = (Produk) FactoryKartu.getKartu(namaItem);
        if (itemToko.containsKey(item)) {
            itemToko.put(item, itemToko.get(item) - 1);
            if (itemToko.get(item) == 0) {
                itemToko.remove(item);
            }
        } else {
            throw new ProdukTidakDijualException("Maaf, Toko tidak menjual produk " + namaItem);
        }

        return item;
    }

    public int jualItem(String namaItem) throws TokoHanyaMembeliProdukException{
        Kartu item = FactoryKartu.getKartu(namaItem);

        if (item.getClass() != Produk.class){
            throw new TokoHanyaMembeliProdukException("Toko tidak membeli barang bertipe " + item.getClass().getName());
        }

        Produk produk = (Produk) item;

        tambahItemKeToko(produk);
        return produk.getHarga();
    }
}
