package com.example.demo.controller;

import com.example.demo.dto.BookError;
import com.example.demo.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<BookError> mapException(BookNotFoundException ex) {
        BookError error = new BookError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
