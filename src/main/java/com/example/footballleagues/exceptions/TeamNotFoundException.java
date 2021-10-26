package com.example.footballleagues.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(String message) {

        super("Not found " + message);
    }

}
