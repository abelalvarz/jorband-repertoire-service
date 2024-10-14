package com.jorband.Repertoire.exceptions;

import com.jorband.Repertoire.dtos.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleRuntimeException(Exception e){

        return new ResponseEntity<>(Response.builder()
                .statusCode(1)
                .success(false)
                .message(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e){

        return new ResponseEntity<>(Response.builder()
                .statusCode(1)
                .success(false)
                .message(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
