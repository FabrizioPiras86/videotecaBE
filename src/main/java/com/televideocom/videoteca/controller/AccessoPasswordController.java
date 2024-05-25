package com.televideocom.videoteca.controller;

import com.televideocom.videoteca.controller.business.AccessoPasswordBusiness;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessoPasswordController {

    @Autowired
    private AccessoPasswordBusiness accessoPasswordBusiness;

    @GetMapping(value = "/api/security", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> controlloAccesso(
            @RequestParam(name = "utente") String utente,
            @RequestParam(name = "password") String password) throws ExternalComunicationErrorException {

        return accessoPasswordBusiness.controlloAccesso(utente, password);
    }
}
