package com.televideocom.videoteca.errors.handler;

import com.televideocom.videoteca.errors.*;
import com.televideocom.videoteca.errors.pojo.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class RestExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotFound(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setStatus(HttpStatus.NOT_FOUND);
        errore.setMessage(ex.getMessage());

        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotValidException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotValid(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setStatus(HttpStatus.BAD_REQUEST);
        errore.setMessage(ex.getMessage());

        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ErrorResponse> exceptionForbidden(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setStatus(HttpStatus.FORBIDDEN);
        errore.setMessage(ex.getMessage());

        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ErrorResponse> exceptionUnauthorized(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setStatus(HttpStatus.UNAUTHORIZED);
        errore.setMessage(ex.getMessage());

        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public final ResponseEntity<ErrorResponse> exceptionInternalServerError(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errore.setMessage(ex.getMessage());

        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ExternalComunicationErrorException.class)
    public final ResponseEntity<ErrorResponse> exceptionExternalComunicationError(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        errore.setMessage(ex.getMessage());

        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
