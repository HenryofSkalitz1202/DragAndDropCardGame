package com.hbd.Deck;

import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.Kartu;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KartuContainer implements Iterable<Kartu> {
    private final List<Kartu> Container;
    private int capacity = -1;

    public KartuContainer(){
        Container = new ArrayList<>();
    }

    public KartuContainer(List<Kartu> _Kartu){
        Container = new ArrayList<>(_Kartu);
    }

    public KartuContainer(int n){
        Container = new ArrayList<>();
        capacity = n;
    }

    public KartuContainer(List<Kartu> _Kartu, int size){
        this(_Kartu);
        capacity = size;
    }

    public int size(){
        return Container.size();
    }

    public int remainingSlot(){
        return capacity - size();
    }

    public Kartu peekKartuAt(int index) throws DeckOutOfBoundsException, DeckEmptyException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        if (index < 0) {throw new DeckOutOfBoundsException("Tidak bisa mengambil pada posisi negatif");}
        if (index > size()) {throw new DeckOutOfBoundsException("Jumlah Deck tidak mencukupi");}
        return Container.get(index);
    }

    public Kartu peekTopKartu() throws DeckEmptyException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        return Container.get(size() - 1);
    }

    public Kartu peekBottomKartu() throws DeckEmptyException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        return Container.get(0);
    }

    public Kartu takeKartuAt(int index) throws DeckEmptyException, DeckOutOfBoundsException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        if (index < 0) {throw new DeckOutOfBoundsException("Tidak bisa mengambil pada posisi negatif");}
        if (index > size()) {throw new DeckOutOfBoundsException("Jumlah Deck tidak mencukupi");}
        return Container.remove(index);
    }

    public Kartu takeTopKartu() throws DeckEmptyException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        return Container.remove(size() - 1);
    }

    public Kartu takeBottomKartu() throws DeckEmptyException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        return Container.remove(0);
    }

    public List<Kartu> takeNTopKartu(int N) throws DeckOutOfBoundsException, DeckEmptyException{
        if (size() == 0) {throw new DeckEmptyException("Deck Kosong");}
        if (N < 0) {throw new DeckOutOfBoundsException("Tidak bisa mengambil pada posisi negatif");}
        if (N > size()) {throw new DeckOutOfBoundsException("Jumlah Deck tidak mencukupi");}
        List<Kartu> result = new ArrayList<Kartu>();

        for (int i = 0; i < N; i++) {
            result.add(takeTopKartu());
        }
        
        return result;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean isFull(){
        return size() == capacity;
    }
    
    public void insertKartu(Kartu kartu) throws DeckPenuhException {
        if (size() == capacity) {throw new DeckPenuhException("Deck sudah penuh");}
        Container.add(kartu);
    }

    public void insertBanyakKartu(List<Kartu> banyakKartu) throws DeckPenuhException{
        if (size() + banyakKartu.size() > capacity && capacity != -1) {throw new DeckPenuhException("Deck sudah penuh");}
        Container.addAll(banyakKartu);
    }

    @Override
    public Iterator<Kartu> iterator() {
        return new KartuContainerIterator();
    }

    public boolean hasAny(Method func) throws Exception{
        for(Kartu kartu : this){
            if ((boolean) func.invoke(kartu)){
                return true;
            }
        }
        return false;
    }

    class KartuContainerIterator implements Iterator<Kartu>{
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public com.hbd.Kartu.Kartu next() {
            try {
                return peekKartuAt(index++);
            } catch (Exception e) {return null;}
        }
    }
}
