package com.hbd.SimpanMuat;

import com.hbd.Kartu.Produk.Produk;
import com.hbd.Pemain.Pemain;
import com.hbd.Toko.Toko;

import java.util.LinkedHashMap;
import java.util.Map;

public class Notaris {

    public GameState getGameState(int currentTurn, Toko toko){
        Map<String, Integer> shopItems = new LinkedHashMap<>();

        for (Map.Entry<Produk, Integer> entry : toko.getItemToko().entrySet()){
            shopItems.put(entry.getKey().getNama(), entry.getValue());
        }

        return new GameState(currentTurn, shopItems);
    }

    public PlayerState getPlayerState(Pemain p){
        return new PlayerState(p.getDuit(), p.getDeck().size(), p.getDeckAktif(), p.getPetakLadang());
    }
}
