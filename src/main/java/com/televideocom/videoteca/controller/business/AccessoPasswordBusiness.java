package com.televideocom.videoteca.controller.business;

import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccessoPasswordBusiness {

    private static final String USER = "Fabrizio";
    private static final String PASSWORD = "Piras";

    public ResponseEntity<String> controlloAccesso(String utente, String password) throws ExternalComunicationErrorException {
        try {
            if (utente.equals(USER) && password.equals(PASSWORD)) {
                return new ResponseEntity<>("Accesso consentito", HttpStatus.NO_CONTENT);
            } else if (utente.isEmpty() || password.isEmpty()) {
                return new ResponseEntity<>("Parametri mancanti", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("Accesso negato", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore esterno");
        }
    }
}

