package com.msvc.product.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, String >> handleException(WebExchangeBindException e) {
        Map<String, String > errorsMap= new HashMap<>();
                e.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(x-> errorsMap.put(x.getField(),x.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorsMap);
    }

}