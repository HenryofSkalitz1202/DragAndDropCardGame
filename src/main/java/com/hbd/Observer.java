package com.hbd;
import com.hbd.Kartu.Kartu;
import java.util.*;

class Observer{
    private ArrayList<Kartu> observers;

    public Observer(){
        this.observers = new ArrayList<Kartu>();
    }

    public ArrayList<Kartu> getObserver(){
        return this.observers;
    }
    
    public void subscribe(Kartu subscriber){
        this.observers.add(subscriber);
    }

    public void unsubscribe(Kartu subscriber){
        this.observers.remove(subscriber);
    }
}