package com.pccw.register.domain.exceptions;

public class BussinessException extends RuntimeException {

    public BussinessException(String message){
        super(message);
    }

    public BussinessException(String message, Throwable cause){
        super(message, cause);
    }

}
