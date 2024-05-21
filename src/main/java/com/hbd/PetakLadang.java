package com.hbd;

import com.hbd.Kartu.Kartu;
import javafx.scene.shape.Rectangle;

public class PetakLadang {
    private Rectangle rect;
    private Kartu item;

    public PetakLadang(Kartu item){
        this.rect = new Rectangle();
        this.item = item;
    }

    public PetakLadang(double x, double y, double w, double h) {
        this.rect = new Rectangle();
        this.rect.setTranslateX(x);
        this.rect.setTranslateY(y);
        this.rect.setWidth(w);
        this.rect.setHeight(h);
    }

    public PetakLadang(double x, double y, double w, double h, Kartu item){
        this.rect = new Rectangle();
        this.rect.setTranslateX(x);
        this.rect.setTranslateY(y);
        this.rect.setWidth(w);
        this.rect.setHeight(h);
        this.item = item;
    }

    public double getX(){
        return this.rect.getTranslateX();
    }

    public double getY(){
        return this.rect.getTranslateY();
    }

    public double getH(){
        return this.rect.getHeight();
    }

    public double getW(){
        return this.rect.getWidth();
    }

    public void setItem(Kartu k){
        this.item = k;
    }

    public Kartu getItem(){
        return this.item;
    }

    public void removeItem(){
        this.item = null;
    }
}
