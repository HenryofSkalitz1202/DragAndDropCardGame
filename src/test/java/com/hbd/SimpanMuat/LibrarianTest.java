// package com.hbd.SimpanMuat;

// import com.hbd.GameEngine;
// import com.hbd.Deck.Deck;
// import com.hbd.Deck.Exception.DeckPenuhException;
// import com.hbd.Kartu.FactoryKartu;
// import com.hbd.Kartu.Makhluk.Makhluk;
// import com.hbd.PetakLadang.Exception.DiluarPetakException;
// import com.hbd.SimpanMuat.Language.Language;
// import com.hbd.PetakLadang.PetakLadangCodeAdapter;
// import org.junit.jupiter.api.Test;
// import java.io.File;
// import java.io.IOException;
// import java.util.*;

// import static org.junit.jupiter.api.Assertions.*;

// class LibrarianTest {

// @Test
// void testSaveAndLoad() throws IOException, DiluarPetakException,
// InterruptedException, DeckPenuhException {
// Librarian librarian = new Librarian();
// GameState gameState = createTestGameState();
// PlayerState player1State = createTestPlayerState();
// PlayerState player2State = createTestPlayerState();

// // Test save and load for each language
// for (int i = 0; i < 3; i++) {
// Language language = librarian.getLanguageAtIndex(i);
// String folderName = "test_" + language.getClass().getSimpleName();

// // Save game state
// librarian.save(gameState, player1State, player2State, language, folderName);

// // Load game state
// librarian.load(language, folderName);

// // Check if the loaded game state matches the original
// GameEngine.resetInstance();
// // GameState loadedGameState = GameEngine.getInstance().getGameState();
// // Delete test folders
// deleteFolder(new File("config" + File.separator + folderName));
// }
// }

// private GameState createTestGameState() {
// Map<String, Integer> shopItems = new LinkedHashMap<>();
// shopItems.put("Labu", 5);
// shopItems.put("Jagung", 10);
// shopItems.put("Ayam", 3);
// return new GameState(10, shopItems);
// }

// private PlayerState createTestPlayerState() throws DiluarPetakException {
// Deck activeDeck = new Deck(6);
// PetakLadangCodeAdapter petakLadang = new PetakLadangCodeAdapter();
// Makhluk makhluk1 = (Makhluk) FactoryKartu.getKartu("Biji Labu");
// Makhluk makhluk2 = (Makhluk) FactoryKartu.getKartu("Ayam");
// petakLadang.setMakhluk("A01", makhluk1);
// petakLadang.setMakhluk("B01", makhluk2);
// return new PlayerState(100, 4, activeDeck, petakLadang.getPetakLadang());
// }

// private void deleteFolder(File folder) {
// if (folder.exists()) {
// File[] files = folder.listFiles();
// if (files != null) {
// for (File file : files) {
// if (file.isDirectory()) {
// deleteFolder(file);
// } else {
// file.delete();
// }
// }
// }
// folder.delete();
// }
// }
// }
