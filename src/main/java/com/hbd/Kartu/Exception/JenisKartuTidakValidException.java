package com.hbd.Kartu.Exception;

public class JenisKartuTidakValidException extends RuntimeException{

    public JenisKartuTidakValidException(String err){
        super(err);
    }
}
