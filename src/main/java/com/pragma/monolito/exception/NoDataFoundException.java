package com.pragma.monolito.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoDataFoundException  extends RuntimeException{

    public NoDataFoundException ( String message){
        super(message);
    }

}
