package com.televideocom.videoteca.controller;

import com.televideocom.videoteca.controller.business.GeneriBusiness;
import com.televideocom.videoteca.entities.Genere;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/generi")
public class GenereController {

    @Autowired
    private GeneriBusiness generiBusiness;

    @GetMapping(value = "/numeroTotaleGeneri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNumeroTotaleGeneri() {
        try {
            int numeroTotaleGeneri = generiBusiness.getNumeroTotaleGeneri();
            return ResponseEntity.ok(numeroTotaleGeneri);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante il recupero del numero totale di generi", e);
        }
    }


    @GetMapping(value = "/genere/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenereById(@PathVariable Long id) throws ExternalComunicationErrorException {
        try {
            Optional<Genere> result = generiBusiness.getFindById(id);
            if (result.isPresent()) {
                return new ResponseEntity<>(result.get(), HttpStatus.OK);
            } else {
                // Se l'Optional è vuoto, restituisci un ResponseEntity con status NOT FOUND
                return new ResponseEntity<>("Genere non trovato", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping(value = "/descrizione", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenereByDescrizione(@RequestParam(name = "descrizione") String descrizione) throws ExternalComunicationErrorException {
        try {
            Object result = generiBusiness.getGenereByDescrizione(descrizione);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException(e.getMessage());
        }
    }

    @GetMapping(value ="/listaGeneri" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Genere>> getGeneri() throws ExternalComunicationErrorException {
        List<Genere> generi = generiBusiness.findAllGeneri();
        if (generi.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(generi, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newGenere(@RequestBody String descrizione) throws ExternalComunicationErrorException {
        try {
            Genere genere = new Genere(null, descrizione, null);

            return new ResponseEntity<>(generiBusiness.saveGenere(genere), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException(e.getMessage());
        }
    }

}
