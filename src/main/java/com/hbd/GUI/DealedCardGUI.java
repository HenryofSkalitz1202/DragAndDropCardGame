package com.hbd.GUI;

import com.hbd.Kartu.Kartu;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class DealedCardGUI extends StackPane {

    private final ImageView imageView;
    private final Text text;
    private final double width;
    private final double height;

    public DealedCardGUI(Kartu kartu) {
        this(120, 180, KartuGUI.getImagePathMap().get(kartu.getNama()), kartu.getNama());
    }

    public DealedCardGUI(double width, double height, String imageUrl, String textContent) {
        this.width = width;
        this.height = height;

        // Create the rectangle with rounded corners
        Rectangle background = new Rectangle(width, height);
        background.setArcWidth(15);
        background.setArcHeight(15);
        background.setFill(Color.color((double) 232 /255, (double) 172 /255, (double) 50 /255));

        // Load the image
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm(), 80, 80, true, true);
        imageView = new ImageView(image);

        // Set image position
        imageView.setTranslateY(-20.0);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        // Create the text element
        text = new Text(textContent);
        text.setWrappingWidth(width - 40); // Adjust for image width and margins
        text.setTextAlignment(TextAlignment.CENTER);

        // Set text position with margins
        StackPane.setMargin(text, new Insets(10 + 80, 20, 20, 20));

        getChildren().addAll(background, imageView, text);
    }

    @Override
    protected double computePrefWidth(double width) {
        return this.width;
    }

    @Override
    protected double computePrefHeight(double height) {
        return this.height;
    }
}