package com.quangduong.orderservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @RestControllerAdvice
    public static class AppExceptionHandler {

        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String handleIllegalArg(IllegalArgumentException ex) {
            return ex.getMessage();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public Map<String, String> handleInvalidArgs(MethodArgumentNotValidException ex) {
            Map<String, String> errorMap = new HashMap<>();
            ex.getBindingResult().getFieldErrors()
                    .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            return errorMap;
        }

        @ExceptionHandler(InternalError.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String handleInternalServerError(InternalError error){
            return error.getMessage();
        }
    }
}
