package com.weather.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class StationExistsException extends RuntimeException {

    public StationExistsException(String message) {
        super(message);
    }
}
