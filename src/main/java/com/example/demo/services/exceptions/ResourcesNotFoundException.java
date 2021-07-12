package com.example.demo.services.exceptions;

/*
Esse é tratamento de exceção para quando procuramos por um ID não existente
 */

public class ResourcesNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourcesNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
