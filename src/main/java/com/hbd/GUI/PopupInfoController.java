package com.hbd.GUI;

import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.GameEngine;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Exception.BelumSiapPanenException;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Pemain.Exception.BerusahaPanenMakhlukGaibException;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;

public class PopupInfoController {

    private PopupInfoMakhluk currentPopup;
    private int last_x;
    private int last_y;
    private boolean last_from_ladang;

    public void popupCard(int x, int y, boolean fromLadang) {

        last_from_ladang = fromLadang;
        last_x = x;
        last_y = y;
        Kartu kartu;

        if (fromLadang) {
            kartu = getFromPetakLadang(x, y);
        } else {
            kartu = getFromDeckAktif(x, y);
        }

        App.getMainPage().phaseOut();
        currentPopup = new PopupInfoMakhluk(kartu, this, fromLadang);
        currentPopup.show();
    }

    public void handleKembali() {
        currentPopup.hide();
        App.getMainPage().phaseIn();
    }

    public void handlePanen() {
        try {
            GameEngine.getInstance().getCurrentPemain().panenPetak(last_x, last_y);
            currentPopup.hide();
            App.getMainPage().phaseIn();
        } catch (BerusahaPanenMakhlukGaibException | DiluarPetakException e) {
            throw new RuntimeException(e);
        } catch (BelumSiapPanenException | DeckPenuhException e) {
            currentPopup.showError(e.getMessage());
        }
    }

    private Makhluk getFromPetakLadang(int x, int y) {
        try {
            return GameEngine.getInstance().getCurrentPemain().getPetakLadang().getMakhluk(x, y);
        } catch (DiluarPetakException e) {
            throw new RuntimeException(e);
        }
    }

    private Kartu getFromDeckAktif(int x, int y) {
        try {
            return GameEngine.getInstance().getCurrentPemain().getDeckAktif().peekKartuAt(x);
        } catch (DeckOutOfBoundsException | DeckEmptyException e) {
            throw new RuntimeException(e);
        }
    }
}
