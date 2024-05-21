package com.hbd;

import com.hbd.SimpanMuat.GameState;
import com.hbd.SimpanMuat.Notaris;
import com.hbd.SimpanMuat.PlayerState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine implements Notaris {
    private static GameEngine instance = null;
    private Pemain pemain1 = null;
    private Pemain pemain2 = null;
    private int nomorTurn = 1;
    private Pemain currentPemain = null;

    private GameEngine(){
        System.out.println("Game Engine Has Been Created");
    }

    public static synchronized GameEngine getInstance()
    {
        if (instance == null)
            instance = new GameEngine();

        return instance;
    }

    public void next(){
        // Logic Next
        // Tambah umur semua tanaman
        nomorTurn++;
        // Shuffle Kartu Hingga Pemain Puas
        // Tentukan apakah akan terjadi serangan beruang
    }

    public void fasePengocokkan(){
        // Shuffle Kartu hingga pemain puas
    }

    public void faseBebas() {
        // Bebas ngapain aja
    }

    @Override
    public GameState getGameState(){
//        return new GameState(nomorTurn, toko.items);
        return new GameState(nomorTurn, new HashMap<>());
    }

//    @Override
//    public PlayerState getPlayerState(int playerNumber) {
//        if (playerNumber == 1){
//            return new PlayerState(pemain1.getDuit(), pemain1.getDeck().size(), pemain1.getDeckAktif(), pemain1.getPetakLadang());
//        } else{
//            return new PlayerState(pemain2.getDuit(), pemain2.getDeck().size(), pemain2.getDeckAktif(), pemain2.getPetakLadang());
//        }
//    }
}
