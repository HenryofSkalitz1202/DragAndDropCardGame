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

    public Kartu(String nama, Rectangle rect){
        this.nama = nama;
        this.x = rect.getLayoutX();
        this.y = rect.getLayoutY();
        this.w = rect.getWidth();
        this.h = rect.getHeight();
        this.rect = rect;
    }

    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public Rectangle getCard() {return this.rect;}

    public void setColor(Color color) {this.rect.setFill(color);}

    public void draw(double translateX, double translateY) {
        rect.setLayoutX(translateX);
        rect.setLayoutY(translateY);
    }

    public String getNama() {
        return this.nama;
    }
}
