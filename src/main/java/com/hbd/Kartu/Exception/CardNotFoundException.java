package com.hbd.Kartu.Exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String err){
        super(err);
    }
}
