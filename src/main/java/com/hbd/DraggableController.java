package com.hbd;

import java.net.URL;
import java.util.ArrayList;
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

    private ArrayList<Ladang> ladang;

    DraggableMaker dm = new DraggableMaker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cards = new ArrayList<Kartu>();
        Kartu k1 = new Kartu("1", active_deck_1.getX(), active_deck_1.getY(), active_deck_1.getWidth(), active_deck_1.getHeight(), rect_kartu_1);
        Kartu k2 = new Kartu("2", active_deck_2.getX(), active_deck_2.getY(), active_deck_2.getWidth(), active_deck_2.getHeight(), rect_kartu_2);
        Kartu k3 = new Kartu("3", active_deck_4.getX(), active_deck_4.getY(), active_deck_4.getWidth(), active_deck_4.getHeight(), rect_kartu_3);
        Kartu k4 = new Kartu("4", petak_ladang_33.getX(), petak_ladang_33.getY(), petak_ladang_33.getWidth(), petak_ladang_33.getHeight(), rect_kartu_4);
        cards.add(k1);
        cards.add(k2);
        cards.add(k3);
        cards.add(k4);

//        ladang = new ArrayList<Ladang>();
//        Ladang l11 = new Ladang(petak_ladang_11.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l12 = new Ladang(petak_ladang_12.getX(), petak_ladang_12.getY(), petak_ladang_12.getWidth(), petak_ladang_12.getHeight(), petak_ladang_12);
//        Ladang l13 = new Ladang(petak_ladang_13.getX(), petak_ladang_13.getY(), petak_ladang_13.getWidth(), petak_ladang_13.getHeight(), petak_ladang_11);
//        Ladang l14 = new Ladang(petak_ladang_14.getX(), petak_ladang_14.getY(), petak_ladang_14.getWidth(), petak_ladang_14.getHeight(), petak_ladang_14);
//        Ladang l15 = new Ladang(petak_ladang_15.getX(), petak_ladang_15.getY(), petak_ladang_15.getWidth(), petak_ladang_15.getHeight(), petak_ladang_15);
//        Ladang l21 = new Ladang(petak_ladang_21.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l22 = new Ladang(petak_ladang_22.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l23 = new Ladang(petak_ladang_23.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l24 = new Ladang(petak_ladang_24.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l25 = new Ladang(petak_ladang_25.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l31 = new Ladang(petak_ladang_31.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l32 = new Ladang(petak_ladang_32.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l33 = new Ladang(petak_ladang_33.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l34 = new Ladang(petak_ladang_34.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l35 = new Ladang(petak_ladang_35.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l41 = new Ladang(petak_ladang_41.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l42 = new Ladang(petak_ladang_42.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l43 = new Ladang(petak_ladang_43.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l44 = new Ladang(petak_ladang_44.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);
//        Ladang l45 = new Ladang(petak_ladang_45.getX(), petak_ladang_11.getY(), petak_ladang_11.getWidth(), petak_ladang_11.getHeight(), petak_ladang_11);

        for (Kartu k: cards) {
            k.getCard().setOnMousePressed(event -> pressed(event, k));
            k.getCard().setOnMouseDragged(event -> dragged(event, k));
            k.getCard().setOnMouseReleased(event -> released(event, k));
        }
    }

    public void pressed(MouseEvent event, Kartu k) {
        k.setColor(Color.GREEN);
    }

    public void dragged(MouseEvent event, Kartu k) {
        k.setX(k.getX() + event.getX());
        k.setY(k.getY() + event.getY());
        k.draw();
    }

    public void released(MouseEvent event, Kartu k) {
        int gridX = (int) k.getX();
        int gridY = (int) k.getY();
        k.setColor(Color.GRAY);
    }
}
