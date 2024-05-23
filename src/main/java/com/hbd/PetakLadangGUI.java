package com.hbd;

import com.hbd.Kartu.Kartu;
import javafx.scene.shape.Rectangle;

public class PetakLadangGUI {
    private final double x;
    private final double y;
    private final double h;
    private final double w;
    private final Rectangle rect;
    private Kartu item;

    public PetakLadangGUI(Rectangle rect) {
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

    public Kartu getItem() {return this.item;}

    public void setItem(Kartu k) {this.item = k;}

    public void removeItem() {this.item = null;}
}
