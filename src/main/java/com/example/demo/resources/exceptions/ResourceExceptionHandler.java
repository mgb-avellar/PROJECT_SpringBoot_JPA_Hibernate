package com.example.demo.resources.exceptions;

import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourcesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice  // Essa anotação intercepta as exceções para que esta classe possa tratá-las
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)  // Essa anotação faz o método abaixo interceptar qualquer exceção do tipo ResourcesNotFoundException
    public ResponseEntity<StandardError> resourceNotFound(ResourcesNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)  // Essa anotação faz o método abaixo interceptar qualquer exceção do tipo DatabaseException
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
