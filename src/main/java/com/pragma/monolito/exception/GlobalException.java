package com.pragma.monolito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {



    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleNoDataException(NoDataFoundException  exception){
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus(HttpStatus.NO_CONTENT.value());
        errorModel.setMessage(exception.getMessage());
        errorModel.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorModel, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleResourceNotFoundException(ResourceNotFoundException  exception){
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus(HttpStatus.NOT_FOUND.value());
        errorModel.setMessage(exception.getMessage());
        errorModel.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleEmptyData(EmptyData  exception){
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus(HttpStatus.BAD_REQUEST.value());
        errorModel.setMessage(exception.getMessage());
        errorModel.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

}
