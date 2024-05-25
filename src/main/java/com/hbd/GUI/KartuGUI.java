package com.hbd.GUI;

import com.hbd.Kartu.Kartu;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

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

    private static Map<String, String> imgPath = Stream.of(new String[][] {
            { "Ayam", "/img/animal/chicken.png" },
            { "Beruang", "/img/animal/bear.png" },
            { "Domba", "/img/animal/sheep.png" },
            { "Hiu Darat", "/img/animal/hiu darat.png" },
            { "Kuda", "/img/animal/horse.png" },
            { "Sapi", "/img/animal/cow.png" },
            { "Accelerate", "/img/item/Accelerate.png" },
            { "Delay", "/img/item/Delay.png" },
            { "Destroy", "/img/item/Destroy.png" },
            { "Instant Harvest", "/img/item/Instant Harvest.png" },
            { "Protect", "/img/item/Protect.png" },
            { "Trap", "/img/item/bear trap.png" },
            { "Biji Jagung", "/img/plant/corn seeds.png" },
            { "Biji Labu", "/img/plant/pumpkin seeds.png" },
            { "Biji Stroberi", "/img/plant/strawberry seeds.png" },
            { "Daging Beruang", "/img/productanimal/Daging Beruang.png" },
            { "Susu", "/img/productanimal/susu.png" },
            { "Daging Kuda", "/img/productanimal/Daging Kuda.png" },
            { "Telur", "/img/productanimal/telur.png" },
            { "Daging Domba", "/img/productanimal/Daging Domba.png" },
            { "Sirip Hiu", "/img/productanimal/shark-fin.png" },
            { "Jagung", "/img/productplant/corn.png" },
            { "Labu", "/img/productplant/pumpkin.png" },
            { "Stroberi", "/img/productplant/strawberry.png" }
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public KartuGUI(Kartu kartu, double x, double y){
        this.kartu = kartu;
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(80, 80);
        this.w = rect.getWidth();
        this.h = rect.getHeight();
        setImg();
        draw();
    }

    public void setImg(){
        Image img = new Image(getClass().getResource(imgPath.get(kartu.getNama())).toExternalForm());
        rect.setFill(new ImagePattern(img));
    }

    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public double getWidth() {return w;}
    public double getHeight() {return h;}

    public Rectangle getRect() {return this.rect;}

    public Kartu getKartu() {return kartu;}

    public void setColor(Color color) {this.rect.setFill(color);}

    public void draw() {
        rect.setTranslateX(x);
        rect.setTranslateY(y);
    }

    public void translate(double translateX, double translateY){
        x += translateX;
        y += translateY;
        draw();
    }

    public String getNama() {
        return this.kartu.getNama();
    }

    public static Map<String, String> getImagePathMap(){
        return imgPath;
    }
}