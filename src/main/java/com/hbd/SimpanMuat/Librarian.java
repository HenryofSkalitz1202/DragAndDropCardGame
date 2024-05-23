package com.hbd.SimpanMuat;

import com.hbd.SimpanMuat.Language.Language;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class Librarian {

    private Map<String, Language> Languages;

    public void save(GameState gameState, PlayerState player1State, PlayerState player2State, Language lang, String FolderName){
        StringBuilder GameStateString = lang.getInitialFormat();
        GameStateString = lang.AddSingularElement(GameStateString, "CURRENT_TURN", String.valueOf(gameState.getCurrentTurn()));

        ArrayList<String> shopItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : gameState.getShopItems().entrySet()){
            StringBuilder currentLine = new StringBuilder();
            currentLine.append(entry.getKey());
            currentLine.append(" ");
            currentLine.append(entry.getValue());
        }

        GameStateString = lang.AddListOfElement(GameStateString, "SHOP_ITEMS", shopItems);

        try {
            PrintWriter writer = new PrintWriter("gamestate.txt");
            writer.print(new String(GameStateString));
        } catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan\n");
        }

        StringBuilder Player1String = lang.getInitialFormat();

        Player1String = lang.AddSingularElement(Player1String, "JUMLAH_GULDEN", String.valueOf(player1State.getDuit()));
        Player1String = lang.AddSingularElement(Player1String, "JUMLAH_DECK", String.valueOf(player1State.getUkuranDeck()));

//        ArrayList<String> deckAktif = new ArrayList<>();
//        for (Map.Entry<String, String> entry : player1State.){
//            StringBuilder currentLine = new StringBuilder();
//            currentLine.append(entry.getKey());
//            currentLine.append(" ");
//            currentLine.append(entry.getValue());
//        }
    }

    public void load(Language lang, String folderPath){
        ;
    }

}
