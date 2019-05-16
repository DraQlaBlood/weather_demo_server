package com.weather.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class RegionExistsException extends RuntimeException {

    public RegionExistsException(String message) {
        super(message);
    }
}
