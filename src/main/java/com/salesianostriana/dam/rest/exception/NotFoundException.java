package com.salesianostriana.dam.rest.exception;

import jakarta.persistence.EntityNotFoundException;

public class NotFoundException extends EntityNotFoundException {

    public NotFoundException(String message){super(message);}
}
