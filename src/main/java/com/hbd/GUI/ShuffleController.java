package com.hbd.GUI;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.GameEngine;
import com.hbd.Kartu.Kartu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShuffleController {

    private List<Kartu> dealedCard;
    private ShufflePage thePage = new ShufflePage(this);

    public Deck getCurrentDeck(){
        return GameEngine.getInstance().getCurrentPemain().getDeck();
    }

    public Deck getCurrentDeckAktif(){
        return GameEngine.getInstance().getCurrentPemain().getDeckAktif();
    }

    public void enterShuffle(){
        try {
            getCurrentDeck().addRandom(4);
        } catch (DeckPenuhException e) {
            throw new RuntimeException(e);
        }
        thePage.switchTo();
        dealCards();
    }

    public void dealCards(){
        dealedCard = new ArrayList<>();
        try {
            getCurrentDeck().shuffle();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int last_dealed_amount = Math.min(getCurrentDeckAktif().remainingSlot(), 4);

        if (last_dealed_amount > getCurrentDeck().size()) {last_dealed_amount = getCurrentDeck().size();}

        try {
            dealedCard = getCurrentDeck().takeNTopKartu(last_dealed_amount);
        } catch (DeckOutOfBoundsException | DeckEmptyException e) {
            throw new RuntimeException("Gagal Deal Kartu for some reason?");
        }
        thePage.showCards(dealedCard);
    }

    public void reshuffleHandler(){
        try {
            getCurrentDeck().insertBanyakKartu(dealedCard);
        } catch (DeckPenuhException e) {
            throw new RuntimeException(e);
        }
        dealCards();
    }

    public void acceptHandler(){
        try {
            getCurrentDeckAktif().insertBanyakKartu(dealedCard);
        } catch (DeckPenuhException e) {
            throw new RuntimeException(e);
        }
        App.getMainPage().switchTo();
    }
}
