package com.hbd;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.hbd.Kartu.Kartu;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.fxml.Initializable;

public class DraggableController implements Initializable {

    @FXML
    private Rectangle active_deck_1;

    @FXML
    private Rectangle active_deck_2;

    @FXML
    private Rectangle active_deck_3;

    @FXML
    private Rectangle active_deck_4;

    @FXML
    private Rectangle active_deck_5;

    @FXML
    private Rectangle active_deck_6;

    @FXML
    private Rectangle rect_kartu_1;

    @FXML
    private Rectangle rect_kartu_2;

    @FXML
    private Rectangle rect_kartu_3;

    @FXML
    private Rectangle rect_kartu_4;

    @FXML
    private Rectangle petak_ladang_11;

    @FXML
    private Rectangle petak_ladang_12;

    @FXML
    private Rectangle petak_ladang_13;

    @FXML
    private Rectangle petak_ladang_14;

    @FXML
    private Rectangle petak_ladang_15;

    @FXML
    private Rectangle petak_ladang_21;

    @FXML
    private Rectangle petak_ladang_22;

    @FXML
    private Rectangle petak_ladang_23;

    @FXML
    private Rectangle petak_ladang_24;

    @FXML
    private Rectangle petak_ladang_25;

    @FXML
    private Rectangle petak_ladang_31;

    @FXML
    private Rectangle petak_ladang_32;

    @FXML
    private Rectangle petak_ladang_33;

    @FXML
    private Rectangle petak_ladang_34;

    @FXML
    private Rectangle petak_ladang_35;

    @FXML
    private Rectangle petak_ladang_41;

    @FXML
    private Rectangle petak_ladang_42;

    @FXML
    private Rectangle petak_ladang_43;

    @FXML
    private Rectangle petak_ladang_44;

    @FXML
    private Rectangle petak_ladang_45;

    private ArrayList<Kartu> cards;

    private ArrayList<PetakLadangGUI> ladang;

    private ArrayList<PetakDeck> dek_aktif;

    private double initialX, initialY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cards = new ArrayList<Kartu>();
        Kartu k1 = new Kartu("1", rect_kartu_1);
        Kartu k2 = new Kartu("2", rect_kartu_2);
        Kartu k3 = new Kartu("3", rect_kartu_3);
        Kartu k4 = new Kartu("4", rect_kartu_4);
        cards.add(k1);
        cards.add(k2);
        cards.add(k3);
        cards.add(k4);

        ladang = new ArrayList<PetakLadangGUI>();
        PetakLadangGUI l11 = new PetakLadangGUI(petak_ladang_11);
        PetakLadangGUI l12 = new PetakLadangGUI(petak_ladang_12);
        PetakLadangGUI l13 = new PetakLadangGUI(petak_ladang_13);
        PetakLadangGUI l14 = new PetakLadangGUI(petak_ladang_14);
        PetakLadangGUI l15 = new PetakLadangGUI(petak_ladang_15);
        PetakLadangGUI l21 = new PetakLadangGUI(petak_ladang_21);
        PetakLadangGUI l22 = new PetakLadangGUI(petak_ladang_22);
        PetakLadangGUI l23 = new PetakLadangGUI(petak_ladang_23);
        PetakLadangGUI l24 = new PetakLadangGUI(petak_ladang_24);
        PetakLadangGUI l25 = new PetakLadangGUI(petak_ladang_25);
        PetakLadangGUI l31 = new PetakLadangGUI(petak_ladang_31);
        PetakLadangGUI l32 = new PetakLadangGUI(petak_ladang_32);
        PetakLadangGUI l33 = new PetakLadangGUI(petak_ladang_33);
        PetakLadangGUI l34 = new PetakLadangGUI(petak_ladang_34);
        PetakLadangGUI l35 = new PetakLadangGUI(petak_ladang_35);
        PetakLadangGUI l41 = new PetakLadangGUI(petak_ladang_41);
        PetakLadangGUI l42 = new PetakLadangGUI(petak_ladang_42);
        PetakLadangGUI l43 = new PetakLadangGUI(petak_ladang_43);
        PetakLadangGUI l44 = new PetakLadangGUI(petak_ladang_44);
        PetakLadangGUI l45 = new PetakLadangGUI(petak_ladang_45);
        ladang.add(l11); ladang.add(l12); ladang.add(l13); ladang.add(l14); ladang.add(l15);
        ladang.add(l21); ladang.add(l22); ladang.add(l23); ladang.add(l24); ladang.add(l25);
        ladang.add(l31); ladang.add(l32); ladang.add(l33); ladang.add(l34); ladang.add(l35);
        ladang.add(l41); ladang.add(l42); ladang.add(l43); ladang.add(l44); ladang.add(l45);

        dek_aktif = new ArrayList<PetakDeck>();
        PetakDeck d1 = new PetakDeck(active_deck_1);
        PetakDeck d2 = new PetakDeck(active_deck_2);
        PetakDeck d3 = new PetakDeck(active_deck_3);
        PetakDeck d4 = new PetakDeck(active_deck_4);
        PetakDeck d5 = new PetakDeck(active_deck_5);
        PetakDeck d6 = new PetakDeck(active_deck_6);
        dek_aktif.add(d1); dek_aktif.add(d2); dek_aktif.add(d3); dek_aktif.add(d4); dek_aktif.add(d5); dek_aktif.add(d6);

        d1.setItem(k1);
        d2.setItem(k2);
        d4.setItem(k3);
        l33.setItem(k4);

        for (Kartu k: cards) {
            k.getCard().setOnMousePressed(event -> pressed(event, k));
            k.getCard().setOnMouseDragged(event -> dragged(event, k));
            k.getCard().setOnMouseReleased(event -> released(event, k));
        }
    }

    public void pressed(MouseEvent event, Kartu k) {
        k.setColor(Color.GREEN);
        initialX = event.getX();
        initialY = event.getY();
        for (PetakLadangGUI p: ladang) {
            if (!Objects.isNull(p.getItem())) {
                if (p.getItem().equals(k)) {
                    p.removeItem();
                    return;
                }
            }
        }
        for (PetakDeck d: dek_aktif) {
            if (!Objects.isNull(d.getItem())) {
                if (d.getItem().equals(k)) {
                    d.removeItem();
                    return;
                }
            }
        }
    }

    public void dragged(MouseEvent event, Kartu k) {
        double translateX = event.getSceneX() - initialX;
        double translateY = event.getSceneY() - initialY;
        k.draw(translateX, translateY);
    }

    public void released(MouseEvent event, Kartu k) {
        k.setColor(Color.GRAY);
        k.setX(k.getCard().getLayoutX());
        k.setY(k.getCard().getLayoutY());
        for (PetakLadangGUI p : ladang) {
            if ((p.getX() < k.getX() && k.getX() < p.getX()+p.getW()) && (p.getY() < k.getY() && k.getY() < p.getY()+p.getH())) {
                k.draw(p.getX(), p.getY());
                p.setItem(k);
                return;
            }
        }
        for (PetakDeck d: dek_aktif) {
            if ((d.getX() < k.getX() && k.getX() < d.getX()+d.getW()) && (d.getY() < k.getY() && k.getY() < d.getY()+d.getH())) {
                k.draw(d.getX(), d.getY());
                d.setItem(k);
                return;
            }
        }
    }
}
