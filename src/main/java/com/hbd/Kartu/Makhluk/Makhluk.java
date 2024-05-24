package com.hbd.Kartu.Makhluk;

import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Exception.BelumSiapPanenException;
import com.hbd.Kartu.Makhluk.Exception.ItemTidakAdaException;
import com.hbd.Kartu.Produk.Produk;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public abstract class Makhluk extends Kartu {
    private int progressPanen;
    private final int maksPanen;
    private final String hasilPanen;
    private List<String> effect;

    public Makhluk(String nama, int maksPanen, String hasilPanen) {
        super(nama, new Rectangle());
        this.progressPanen = 0;
        this.maksPanen = maksPanen;
        this.hasilPanen = hasilPanen;
        this.effect = new ArrayList<>();
    }

    public int getProgressPanen() {
        return this.progressPanen;
    }

    public int getMaksPanen() {
        return this.maksPanen;
    }

    public String getHasilPanen() {
        return this.hasilPanen;
    }

    public List<String> getEffect(){
        return effect;
    }

    public void setEffect(List<String> _effect) {effect = new ArrayList<>(_effect);}

    public void setProgressPanen(int progressPanen) {
        this.progressPanen = progressPanen;
    }

    public boolean siapPanen(){return progressPanen >= maksPanen;}

    public abstract Produk panen() throws BelumSiapPanenException;

    public abstract void hisabItem(Item item) throws ItemTidakAdaException;
}
