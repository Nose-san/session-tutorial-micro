package com.example.sessiontutorial.apinfra.exception;

public class ScheduleFullException extends BusinessException{
    public ScheduleFullException(String code, String message){
        super(code, message);
    }
}
