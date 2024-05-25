package com.hbd.Kartu;

import java.util.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class KartuGUI{
    private Kartu kartu;
    private Rectangle rect;
    private static final HashMap<String, String> imagePaths = new HashMap<String, String>();

    public static void initializePaths(){
        imagePaths.put("Hiu Darat", "img\\animal\\hiu darat.png");
        imagePaths.put("Kuda", "img\\animal\\horse.png");
        imagePaths.put("Domba", "img\\animal\\sheep.png");
        imagePaths.put("Sapi", "img\\animal\\cow.png");
        imagePaths.put("Ayam", "img\\animal\\chicken.png");
        imagePaths.put("Beruang", "img\\animal\\bear.png");
        imagePaths.put("Biji Jagung", "img\\plant\\corn seeds.png");
        imagePaths.put("Biji Labu", "img\\plant\\pumpkin seeds.png");
        imagePaths.put("Biji Stroberi", "img\\plant\\strawberry seeds.png");
        imagePaths.put("Sirip Hiu", "img\\productanimal\\shark-fin.png");
        imagePaths.put("Susu", "img\\productanimal\\susu.png");
        imagePaths.put("Daging Domba", "img\\productanimal\\Daging Domba.png");
        imagePaths.put("Daging Kuda", "img\\productanimal\\Daging Kuda.png");
        imagePaths.put("Telur", "img\\productanimal\\telur.png");
        imagePaths.put("Daging Beruang", "img\\productanimal\\Daging Beruang.png");
        imagePaths.put("Jagung", "img\\productplant\\corn.png");
        imagePaths.put("Labu", "img\\productplant\\pumpkin.png");
        imagePaths.put("Stroberi", "img\\productplant\\strawberry.png");
        imagePaths.put("Accelerate", "img\\item\\Accelerate.png");
        imagePaths.put("Delay", "img\\item\\Delay.png");
        imagePaths.put("Instant Harvest", "img\\item\\Instant Harvest.png");
        imagePaths.put("Destroy", "img\\item\\Destroy.png");
        imagePaths.put("Protect", "img\\item\\Protect.png");
        imagePaths.put("Trap", "img\\item\\bear trap.png");
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