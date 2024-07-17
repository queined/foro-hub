package com.inarixdono.forohub.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.auth0.jwt.exceptions.JWTVerificationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionDTO> handleJWTExpiration(JWTVerificationException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDTO> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<ExceptionDTO> handlePasswordsDoNotMatch(PasswordsDoNotMatchException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage()));
    }
}
