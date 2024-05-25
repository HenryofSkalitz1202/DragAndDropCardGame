package com.hbd.SimpanMuat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameStateTest {

    private GameState gameState;
    private int currentTurn;
    private Map<String, Integer> shopItems;

    @BeforeEach
    void setUp() {
        currentTurn = 5;
        shopItems = new HashMap<>();
        shopItems.put("Sirip Hiu", 10);
        shopItems.put("Jagung", 5);
        shopItems.put("Telur", 20);

        gameState = new GameState(currentTurn, shopItems);
    }

    @Test
    void testGameStateConstructor() {
        assertNotNull(gameState);
    }

    @Test
    void testGetCurrentTurn() {
        assertEquals(currentTurn, gameState.getCurrentTurn());
    }

    @Test
    void testGetShopItems() {
        Map<String, Integer> retrievedShopItems = gameState.getShopItems();
        assertNotNull(retrievedShopItems);
        assertEquals(shopItems.size(), retrievedShopItems.size());
        assertEquals(shopItems.get("Sirip Hiu"), retrievedShopItems.get("Sirip Hiu"));
        assertEquals(shopItems.get("Shield"), retrievedShopItems.get("Shield"));
        assertEquals(shopItems.get("Telur"), retrievedShopItems.get("Telur"));
    }

    @Test
    void testShopItemsAreImmutable() {

        assertEquals(shopItems.size(), gameState.getShopItems().size());
        assertEquals(shopItems.get("Sirip Hiu"), gameState.getShopItems().get("Sirip Hiu"));
        assertEquals(shopItems.get("Shield"), gameState.getShopItems().get("Shield"));
        assertEquals(shopItems.get("Telur"), gameState.getShopItems().get("Telur"));

    }
}
