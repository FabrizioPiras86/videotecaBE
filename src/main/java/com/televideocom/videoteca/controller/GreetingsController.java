package com.televideocom.videoteca.controller;

import com.televideocom.videoteca.controller.pojo.GreetingPojo;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingsController {

    /**
     * Metodo che restituisce un oggetto GreetingPojo contenente il saluto "Hello World!".
     * @return Un oggetto GreetingPojo con la descrizione "Hello World!".
     */
    @GetMapping(value = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingPojo greetings() throws ExternalComunicationErrorException {
        try {
            return new GreetingPojo("Greetings");
        }catch (Exception e){
            throw new ExternalComunicationErrorException("Errore esterno");
        }
    }
}
