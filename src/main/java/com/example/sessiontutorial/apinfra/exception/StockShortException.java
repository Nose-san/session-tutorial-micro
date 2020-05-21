package com.example.sessiontutorial.apinfra.exception;

public class StockShortException extends BusinessException{

    public StockShortException(String code, String message){
        super(code, message);
    }

}
