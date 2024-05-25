package com.hbd.SimpanMuat;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.GameEngine;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadangCodeAdapter;
import com.hbd.SimpanMuat.Language.Language;
import com.hbd.SimpanMuat.Language.Txt;

import java.io.*;
import java.util.*;

public class Librarian {

    private List<Language> Languages;
    private Scholar otherPersonality;

    public Librarian() {
        Languages = new ArrayList<>();
        otherPersonality = new Scholar();
        Languages.add(new Txt());
    }

    public void save(GameState gameState, PlayerState player1State, PlayerState player2State, Language lang,
            String FolderName) throws IOException {
        Object gameStateObj = lang.getInitialFormat();

        gameStateObj = lang.AddElement(gameStateObj, "CURRENT_TURN", String.valueOf(gameState.getCurrentTurn()));

        List<Map<String, String>> formattedShopItems = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : gameState.getShopItems().entrySet()) {
            formattedShopItems.add(new LinkedHashMap<>());
            formattedShopItems.get(formattedShopItems.size() - 1).put("NAMA_PRODUK", entry.getKey());
            formattedShopItems.get(formattedShopItems.size() - 1).put("JUMLAH_PRODUK",
                    String.valueOf(entry.getValue()));
        }

        gameStateObj = lang.AddElement(gameStateObj, "SHOP_ITEMS", formattedShopItems);

        String gameStateString = lang.LanguageObjectToString(gameStateObj);

        Object player1Obj = lang.getInitialFormat();

        player1Obj = lang.AddElement(player1Obj, "JUMLAH_GULDEN", String.valueOf(player1State.getDuit()));
        player1Obj = lang.AddElement(player1Obj, "JUMLAH_DECK", String.valueOf(player1State.getUkuranDeck()));
        player1Obj = lang.AddElement(player1Obj, "ACTIVE_DECK_CARDS", player1State.getActiveDeckEntries());
        player1Obj = lang.AddElement(player1Obj, "LADANG_CARDS", player1State.getLadangItems());

        String player1String = lang.LanguageObjectToString(player1Obj);

        Object player2Obj = lang.getInitialFormat();

        player2Obj = lang.AddElement(player2Obj, "JUMLAH_GULDEN", String.valueOf(player2State.getDuit()));
        player2Obj = lang.AddElement(player2Obj, "JUMLAH_DECK", String.valueOf(player2State.getUkuranDeck()));
        player2Obj = lang.AddElement(player2Obj, "ACTIVE_DECK_CARDS", player2State.getActiveDeckEntries());
        player2Obj = lang.AddElement(player2Obj, "LADANG_CARDS", player2State.getLadangItems());

        String player2String = lang.LanguageObjectToString(player2Obj);

        File folder = new File("config" + File.separator + FolderName);
        folder.mkdirs();

        writeToFileInFolder(gameStateString,
                "config" + File.separator + FolderName + File.separator + "gamestate." + lang.getExtension());
        writeToFileInFolder(player1String,
                "config" + File.separator + FolderName + File.separator + "player1." + lang.getExtension());
        writeToFileInFolder(player2String,
                "config" + File.separator + FolderName + File.separator + "player2." + lang.getExtension());
    }

    public void load(Language lang, String folderPath)
            throws FileNotFoundException, DiluarPetakException, DeckPenuhException {
        GameState gameState = loadGameState(lang, "config" + File.separator + folderPath);
        PlayerState player1State = loadPlayerState(lang, "config" + File.separator + folderPath, 1);
        PlayerState player2State = loadPlayerState(lang, "config" + File.separator + folderPath, 2);
        GameEngine.resetInstance();
        GameEngine.loadInstance(gameState, player1State, player2State);
    }

    public List<String> getPossibleLanguages() {
        List<String> result = new ArrayList<>();

        for (Language language : Languages) {
            result.add(language.getClass().toString());
        }

        return result;
    }

    public Language getLanguageAtIndex(int idx) {
        return Languages.get(idx);
    }

    public List<String> getLanguagesOptions() {
        List<String> result = new ArrayList<>();
        for (Language language : Languages) {
            result.add(language.getExtension());
        }
        return result;
    }

    public void study(String JarPath) throws IOException {
        List<Object> ClassGotten = otherPersonality.loadClasses(JarPath);

        for (Object o : ClassGotten) {
            try {
                Language lang = (Language) o;
                Languages.add(lang);
            } catch (Exception e) {
                /* Skip Aja Lah Kalo Merusak */}
        }
    }

    private GameState loadGameState(Language lang, String folderPath) throws FileNotFoundException {
        File gameStateFile = new File(folderPath + File.separator + "gamestate." + lang.getExtension());

        StringBuilder content = new StringBuilder();
        Scanner reader = new Scanner(gameStateFile);
        while (reader.hasNextLine()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        Map<String, Object> resultMap = lang.parseElements(new String(content));

        List<Map<String, String>> shopItems = (List<Map<String, String>>) resultMap.get("SHOP_ITEMS");

        Map<String, Integer> finalShopItems = new HashMap<>();

        for (Map<String, String> shopItem : shopItems) {
            finalShopItems.put(shopItem.get("NAMA_PRODUK"), Integer.valueOf(shopItem.get("JUMLAH_PRODUK")));
        }

        return new GameState(Integer.parseInt((String) resultMap.get("CURRENT_TURN")), finalShopItems);
    }

    private PlayerState loadPlayerState(Language lang, String folderPath, int playerNumber)
            throws FileNotFoundException, DeckPenuhException, DiluarPetakException {
        File playerFile = new File(folderPath + File.separator + "player" + playerNumber + "." + lang.getExtension());

        StringBuilder content = new StringBuilder();
        Scanner reader = new Scanner(playerFile);
        while (reader.hasNextLine()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        Map<String, Object> resultMap = lang.parseElements(new String(content));

        int gulden = Integer.parseInt((String) resultMap.get("JUMLAH_GULDEN"));
        int deckSize = Integer.parseInt((String) resultMap.get("JUMLAH_DECK"));

        List<Map<String, String>> active_deck_cards = (List<Map<String, String>>) resultMap.get("ACTIVE_DECK_CARDS");

        Deck activeDeck = new Deck(6);
        for (int i = 0; i < 6; i++) {
            char code = (char) ('A' + i);
            for (Map<String, String> map : active_deck_cards) {
                if (map.get("LOKASI_KARTU").equals(code + "01")) {
                    activeDeck.insertKartu(FactoryKartu.getKartu(map.get("NAMA_KARTU")));
                }
            }
        }

        List<Map<String, Object>> ladang_cards = (List<Map<String, Object>>) resultMap.get("LADANG_CARDS");

        PetakLadangCodeAdapter petakLadangCodeAdapter = new PetakLadangCodeAdapter();

        for (Map<String, Object> ladang_card : ladang_cards) {
            Makhluk maboi = (Makhluk) FactoryKartu.getKartu(((String) ladang_card.get("NAMA_KARTU")));

            if (ladang_card.get("PROGRESS_PANEN").getClass() == Integer.class) {
                maboi.setProgressPanen((Integer) ladang_card.get("PROGRESS_PANEN"));
            } else {
                maboi.setProgressPanen(Integer.parseInt((String) ladang_card.get("PROGRESS_PANEN")));
            }
            maboi.setEffect((List<String>) ladang_card.get("ITEM_AKTIF"));
            petakLadangCodeAdapter.setMakhluk((String) ladang_card.get("LOKASI_KARTU"), maboi);
        }

        return new PlayerState(gulden, deckSize, activeDeck, petakLadangCodeAdapter.getPetakLadang());
    }

    private void writeToFileInFolder(String content, String filePath) throws IOException {
        File file = new File(filePath);

        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

}
