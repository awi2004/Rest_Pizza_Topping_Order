package de.tub.ise.anwsys.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class exception_inavlidInput extends RuntimeException {
    public exception_inavlidInput(String message){
        super(message);
    }
}
