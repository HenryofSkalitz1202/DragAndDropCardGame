package com.hbd.Kartu;

import java.util.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class KartuGUI{
    private Kartu kartu;
    private Rectangle rect;
    private static final HashMap<String, String> imagePaths = new HashMap<String, String>();

    private static void initializePaths(){
        imagePaths.put("Hiu Darat", "img\\animal\\hiu_darat.jpg");
        imagePaths.put("Kuda", "img\\animal\\horse.jpg");
        imagePaths.put("Domba", "img\\animal\\domba.jpg");
        imagePaths.put("Sapi", "img\\animal\\sapi.jpg");
        imagePaths.put("Ayam", "img\\animal\\chicken.jpg");
        imagePaths.put("Beruang", "img\\animal\\bear.jpg");
        imagePaths.put("Biji Jagung", "img\\plant\\corn_kernel.jpg");
        imagePaths.put("Biji Labu", "img\\plant\\pumpkin_seed.png");
        imagePaths.put("Biji Stroberi", "img\\plant\\strawberry_seed.jpg");
        imagePaths.put("Sirip Hiu", "img\\productanimal\\shark_fin.jpg");
        imagePaths.put("Susu", "img\\productanimal\\milk.png");
        imagePaths.put("Daging Domba", "img\\productanimal\\mutton.jpg");
        imagePaths.put("Daging Kuda", "img\\productanimal\\horse_meat.png");
        imagePaths.put("Telur", "img\\productanimal\\egg.jpg");
        imagePaths.put("Daging Beruang", "img\\productanimal\\bear_meat.jpg");
        imagePaths.put("Jagung", "img\\productplant\\corn.jpg");
        imagePaths.put("Labu", "img\\productplant\\pumpkin.jpg");
        imagePaths.put("Stroberi", "img\\productplant\\strawberry.png");
        imagePaths.put("Accelerate", "img\\item\\accelerate.png");
        imagePaths.put("Delay", "img\\item\\delay.jpg");
        imagePaths.put("Instant Harvest", "img\\item\\harvest.JPG");
        imagePaths.put("Destroy", "img\\item\\destroy.jpg");
        imagePaths.put("Protect", "img\\item\\protect.jpg");
        imagePaths.put("Trap", "img\\item\\trap.jpg");
    }

    public KartuGUI(Kartu kartu){
        this.kartu = kartu;
        this.rect = new Rectangle();
    }

    public void setX(double x){
        this.rect.setTranslateX(x);
    }
    
    public void setY(double y){
        this.rect.setTranslateY(y);
    }
    
    public double getX(){
        return this.rect.getTranslateX();
    }
    
    public double getY(){
        return this.rect.getTranslateY();
    }
    
    public Rectangle getCard(){
        return this.rect;
    }
    
    public void setColor(Color color){
        this.rect.setFill(color);
    }

    public String getImagePath(){
        return imagePaths.get(this.kartu.getNama());
    }
}