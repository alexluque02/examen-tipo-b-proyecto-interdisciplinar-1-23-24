package com.salesianostriana.dam.rest.security.errorhandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.rest.exception.NotFoundException;
import com.salesianostriana.dam.rest.exception.PasswordDoNotMatchException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class TokenControllerAdvice  {

    @ExceptionHandler({ AuthenticationException.class })
    public ErrorResponse handleAuthenticationException(AuthenticationException ex) {
        return ErrorResponse.builder(ex, HttpStatus.UNAUTHORIZED, "UNAUTHORIZED")
                .type(URI.create("http://api-examen/error/unauthorized"))
                .property("timestamp", LocalDateTime.now())
                .title("No tienes autorización")
                .build();
        /*return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("WWW-Authenticate", "Bearer")
                .body(ErrorMessage.of(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI()));*/

    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        return ErrorResponse.builder(ex, HttpStatus.FORBIDDEN, "ACCESS DENIED")
                .type(URI.create("http://api-examen/error/access-denied"))
                .property("timestamp", LocalDateTime.now())
                .title("Acceso denegado")
                .build();

    }


    @ExceptionHandler({JwtTokenException.class})
    public ErrorResponse handleTokenException(JwtTokenException ex) {
        return ErrorResponse.builder(ex, HttpStatus.FORBIDDEN, "TOKEN EXCEPTION")
                .type(URI.create("http://api-examen/error/token"))
                .property("timestamp", LocalDateTime.now())
                .title("El token proporcionado no tiene acceso")
                .build();
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ErrorResponse handleUserNotExistsException(UsernameNotFoundException ex) {
        return ErrorResponse.builder(ex, HttpStatus.FORBIDDEN, "USER NOT EXISTS")
                .type(URI.create("http://api-examen/error/user-not-exists"))
                .property("timestamp", LocalDateTime.now())
                .title("El nombre de usuario no existe")
                .build();
    }



    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex){
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, "ENTITY NOT FOUND")
                .type(URI.create("http://api-examen/error/not-found"))
                .property("timestamp", LocalDateTime.now())
                .title("La entidad no se encuentra")
                .build();
    }

    @ExceptionHandler({NotFoundException.class})
    public ErrorResponse handleEntityNotFoundPersonalException(NotFoundException ex){
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, "ENTITY NOT FOUND")
                .type(URI.create("http://api-examen/error/not-found"))
                .property("timestamp", LocalDateTime.now())
                .title("La entidad no se encuentra")
                .detail(ex.getMessage())
                .build();
    }

    @ExceptionHandler({PasswordDoNotMatchException.class})
    public ErrorResponse handlePasswordsDoNotMatchException(PasswordDoNotMatchException ex){
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, "PASSWORD INCORRECT")
                .type(URI.create("http://api-examen/error/not-found"))
                .property("timestamp", LocalDateTime.now())
                .title("Passwords do not match")
                .detail(ex.getMessage())
                .build();
    }

}
