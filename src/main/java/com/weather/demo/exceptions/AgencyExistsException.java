package com.weather.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class AgencyExistsException extends RuntimeException {

    public AgencyExistsException(String message) {
        super(message);
    }
}
