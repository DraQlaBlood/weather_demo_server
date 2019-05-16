package com.weather.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class MetricExistsException extends RuntimeException {
    public MetricExistsException(String message) {
        super(message);
    }
}
