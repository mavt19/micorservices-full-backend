package com.msvc.order.exceptionhandler;

import com.msvc.order.util.exceptions.NotFoundException;
import com.msvc.order.util.exceptions.ServerRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
                .forEach(x-> errorsMap.put(x.getField(),x.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorsMap);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody Map<String, String> handlerNotFoundException(Exception exception){
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("message", exception.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServerRestException.class)
    public @ResponseBody Map<String, String> handlerServerRestException(Exception exception){
    Map<String, String> errors = new HashMap<String, String>();
    errors.put("message", exception.getCause().getMessage());
    return errors;
    }
}