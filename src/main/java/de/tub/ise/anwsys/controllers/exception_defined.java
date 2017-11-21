
package de.tub.ise.anwsys.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class exception_defined extends RuntimeException {
    public exception_defined() {
        super();
    }

    public exception_defined( String message) {
        super(message);
    }
}
