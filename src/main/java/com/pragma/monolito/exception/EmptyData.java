package com.pragma.monolito.exception;

public class EmptyData extends RuntimeException{

    public EmptyData (String message){
        super(message);
    }
}
