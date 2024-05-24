package com.hbd.GUI;

import com.hbd.Kartu.Kartu;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KartuGUI {
    private Kartu kartu;
    private double x;
    private double y;
    private double h;
    private double w;
    private Rectangle rect;
    private Map<String, String> imgPath = Stream.of(new String[][] {
            { "Ayam", "/img/animal/chicken.jpg" },
            { "Beruang", "/img/animal/bear.jpg" },
            { "Domba", "/img/animal/domba.jpg" },
            { "Hiu Darat", "/img/animal/hiu_darat.jpg" },
            { "Kuda", "/img/animal/horse.jpg" },
            { "Cow", "/img/animal/cow.jpg" },
            { "Accelerate", "/img/item/accelerate.png" },
            { "Delay", "/img/item/delay.jpg" },
            { "Destroy", "/img/item/destroy.jpg" },
            { "Harvest", "/img/item/harvest.JPG" },
            { "Protect", "/img/item/protect.jpg" },
            { "Trap", "/img/item/trap.jpg" },
            { "Biji Jagung", "/img/plant/corn_kernel.jpg" },
            { "Biji Labu", "/img/plant/pumpkin_seed.png" },
            { "Biji Stroberi", "/img/plant/strawberry_seed.jpg" },
            { "Daging Beruang", "/img/productanimal/bear_meat.jpg" },
            { "Susu", "/img/productanimal/milk.png" },
            { "Daging Kuda", "/img/productanimal/horse_meat.png" },
            { "Telur", "/img/productanimal/egg.jpg" },
            { "Daging Domba", "/img/productanimal/mutton.jpg" },
            { "Sirip Hiu", "/img/productanimal/shark_fin.jpg" },
            { "Jagung", "/img/productplant/corn.jpg" },
            { "Labu", "/img/productplant/pumpkin.jpg" },
            { "Stroberi", "/img/productplant/strawberry.png" }
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public KartuGUI(Kartu kartu, Rectangle rect){
        this.kartu = kartu;
        this.x = rect.getLayoutX();
        this.y = rect.getLayoutY();
        this.w = rect.getWidth();
        this.h = rect.getHeight();
        this.rect = rect;
        setImg();
    }

    public void setImg(){
        Image img = new Image(getClass().getResource(imgPath.get(kartu.getNama())).toExternalForm());
        rect.setFill(new ImagePattern(img));
    }

    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public Rectangle getCard() {return this.rect;}

    public Kartu getKartu() {return kartu;}

    public void setColor(Color color) {this.rect.setFill(color);}

    public void draw(double translateX, double translateY) {
        rect.setLayoutX(translateX);
        rect.setLayoutY(translateY);
    }

    public String getNama() {
        return this.kartu.getNama();
    }
}