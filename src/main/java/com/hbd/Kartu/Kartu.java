package com.hbd.Kartu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Kartu {
    private final String nama;
    private double x;
    private double y;
    private double h;
    private double w;
    private Rectangle rect;

    public Kartu(String nama, double x, double y, double w, double h, Rectangle rect){
        this.nama = nama;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rect = rect;
    }

    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public Rectangle getCard() {return this.rect;}

    public void setColor(Color color) {this.rect.setFill(color);}

    public void draw() {
        rect.setWidth(this.w);
        rect.setHeight(this.h);
        rect.setTranslateX(this.x);
        rect.setTranslateY(this.y);
    }

    public String getNama() {
        return this.nama;
    }
}
