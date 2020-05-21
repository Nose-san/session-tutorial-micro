package com.example.sessiontutorial.apinfra.exception;

import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CommonExceptionHandler {

    @Autowired
    Mapper beanMapper;


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException businessException){


        return new ResponseEntity<>(
                beanMapper.map(businessException, BusinessExceptionResponse.class),
                HttpStatus.BAD_REQUEST);

    }

    /*

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleMyException(BusinessException businessException, WebRequest request) {

        return handleExceptionInternal(
                businessException,
                beanMapper.map(businessException, BusinessExceptionResponse.class),
                null,
                HttpStatus.BAD_REQUEST,
                request);

    }

     */


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Object> handleValidationException(Exception exception){

        BindingResult bindingResult = null;

        if(exception instanceof MethodArgumentNotValidException){
            bindingResult = ((MethodArgumentNotValidException)exception).getBindingResult();
        }else if(exception instanceof BindException){
            bindingResult = ((BindException)exception).getBindingResult();
        }

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return new ResponseEntity<>(
                ValidationErrorResponse.builder()
                        .validationErrors(ValidationErrorMapper.map(fieldErrors))
                        .build(),
                HttpStatus.BAD_REQUEST);

    }



}