package com.example.sessiontutorial.apinfra.exception;

public class CreditLimitOverException extends BusinessException{
        public CreditLimitOverException(String code, String message){
            super(code, message);
        }
}
