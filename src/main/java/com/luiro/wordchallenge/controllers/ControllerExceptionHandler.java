package com.luiro.wordchallenge.controllers;

import com.luiro.wordchallenge.domain.exceptions.InvalidCharactersException;
import com.luiro.wordchallenge.domain.exceptions.InvalidRelationshipException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InvalidRelationshipException.class)
    public ResponseEntity<Object> handleInvalidRelationshipException(
            InvalidRelationshipException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "The relationship between these two words already exists");

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidCharactersException.class)
    public ResponseEntity<Object> handleInvalidCharactersException(
            InvalidCharactersException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Words can only be composed from A to Z characters (upper or lowercase) and spaces");

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
