package com.hbd.GUI;

import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Hewan;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Makhluk.Tanaman;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PopupInfoMakhluk extends AnchorPane {

    private PopupInfoController controller;

    public PopupInfoMakhluk(Kartu kartu, PopupInfoController ctr, boolean Panenable){

            controller = ctr;

            String nama = kartu.getNama();

            Text namaLabel = new Text(nama);
            namaLabel.setFont(Font.font("Arial", 20));

            Image kartuimg = new Image(getClass().getResource(KartuGUI.getImagePathMap().get(nama)).toExternalForm());
            ImageView kartuView = new ImageView(kartuimg);
            kartuView.setFitWidth(112);
            kartuView.setFitHeight(112);

            Rectangle kartuClip = new Rectangle(
                    kartuView.getFitWidth(), kartuView.getFitHeight()
            );
            kartuClip.setArcWidth(20);
            kartuClip.setArcHeight(20);
            kartuView.setClip(kartuClip);

            Image bgimg = new Image(getClass().getResource("img/bgPopup.png").toExternalForm());
            ImageView bg = new ImageView(bgimg);
            bg.setFitWidth(489);
            bg.setFitHeight(242);

            Rectangle bgClip = new Rectangle(
                    bg.getFitWidth(), bg.getFitHeight()
            );

            bgClip.setArcHeight(89);
            bgClip.setArcWidth(89);
            bg.setClip(bgClip);

            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage bgTemp = bg.snapshot(parameters, null);

            bg.setClip(null);

            ColorAdjust adj = new ColorAdjust(0.0, 0, -0.2, 0);
            Blend bl = new Blend(BlendMode.ADD, (Effect) new GaussianBlur(4), (Effect) new DropShadow(20, Color.BLACK));
            adj.setInput(bl);
            bg.setEffect(adj);
            bg.setImage(bgTemp);

            Button kembaliButton = new Button("Kembali");
            kembaliButton.setPrefWidth(100);
            kembaliButton.setOnAction(actionEvent -> ctr.handleKembali());

            place(bg, 0.0, 0.0);
            place(namaLabel, 42.0, 30.0);
            place(kartuView, 33.0, 65.0);
            place(kembaliButton, 344.0, 21.0);

            if (Panenable) {
                Button panenButton = new Button("Panen");
                panenButton.setPrefWidth(100);
                panenButton.setOnAction(actionEvent -> ctr.handlePanen());
                place(panenButton, 197.0, 192.0);
            }

            if (kartu instanceof Makhluk) {
                String progressContent = "";
                if (kartu instanceof Hewan){
                    progressContent += "Berat: ";
                } else if (kartu instanceof Tanaman) {
                    progressContent += "Umur: ";
                }

                Text progressLabel = new Text(progressContent + ((Makhluk) kartu).getProgressPanen());
                progressLabel.setFont(Font.font("Arial", 20));

                StringBuilder effects = new StringBuilder();
                effects.append("Effects :");
                for(String effect : ((Makhluk) kartu).getEffect()){
                    effects.append(' ');
                    effects.append(effect);
                }

                Text effectsLabel = new Text(effects.toString());
                effectsLabel.setFont(Font.font("Arial", 20));
                effectsLabel.setWrappingWidth(234);
                place(progressLabel, 174.0, 71.0);
                place(effectsLabel, 174.0, 102.0);
            }

            AnchorPane.setLeftAnchor(this, 200.0);
            AnchorPane.setTopAnchor(this, 213.0);
    }

    public void show(){
        App.getPane().getChildren().add(this);
    }

    public void hide(){
        App.getPane().getChildren().remove(this);
    }

    public void showError(String err){
        TextField errorText = new TextField(err);
        errorText.setAlignment(Pos.CENTER);
        errorText.setPrefWidth(489);
        errorText.setFont(Font.font("Arial", 24));
        errorText.setStyle("-fx-text-inner-color: red; -fx-background-color: transparent;");
        place(errorText, 0.0, -45.0);
    }

    private void place(Node a, Double x, Double y){
        AnchorPane.setTopAnchor(a, y);
        AnchorPane.setLeftAnchor(a, x);
        getChildren().add(a);
    }

}
