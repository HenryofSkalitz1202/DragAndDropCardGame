package com.hbd;

import com.hbd.Kartu.Kartu;
import javafx.scene.shape.Rectangle;

public class PetakLadang {
    private double x;
    private double y;
    private double h;
    private double w;
    private Rectangle rect;
    private Kartu item;

    public PetakLadang(double x, double y, double w, double h, Rectangle rect) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rect = rect;
    }

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public double getH() {return this.h;}

    public double getW() {return this.w;}

    public Kartu getItem() {return this.item;}

    public void setItem(Kartu k) {this.item = k;}

    public void removeItem() {this.item = null;}
}
