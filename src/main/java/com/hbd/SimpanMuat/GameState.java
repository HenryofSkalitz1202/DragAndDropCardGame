package com.hbd.SimpanMuat;

import java.util.Map;

public class GameState {
    private final int currentTurn;
    private final Map<String, Integer> shopItems;

    public GameState(int _currentTurn, Map<String, Integer> _shopItems){
        currentTurn = _currentTurn;
        shopItems = _shopItems;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
    public Map<String, Integer> getShopItems() {
        return shopItems;
    }
}
