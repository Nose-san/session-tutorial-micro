package com.example.sessiontutorial.apinfra.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BusinessExceptionResponse{

    //private BusinessException businessException;

    private String code;
    private Object[] args;
    private String message;
}