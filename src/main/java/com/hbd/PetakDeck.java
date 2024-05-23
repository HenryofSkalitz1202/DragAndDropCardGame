package com.hbd;

import com.hbd.Kartu.Kartu;
import javafx.scene.shape.Rectangle;

public class PetakDeck {
    private double x;
    private double y;
    private double h;
    private double w;
    private Rectangle rect;
    private Kartu item;

    public PetakDeck(Rectangle rect) {
        this.x = rect.getLayoutX();
        this.y = rect.getLayoutY();
        this.w = rect.getWidth();
        this.h = rect.getHeight();
        this.rect = rect;
    }

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public double getH() {return this.h;}

    public double getW() {return this.w;}

    public void setItem(Kartu k) {this.item = k;}

    public Kartu getItem() {return this.item;}

    public void removeItem() {this.item = null;}
}
