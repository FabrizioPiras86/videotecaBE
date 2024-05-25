package com.televideocom.videoteca.controller;

import com.televideocom.videoteca.controller.business.InterpreteBusiness;
import com.televideocom.videoteca.controller.pojo.InterpreteRequest;
import com.televideocom.videoteca.entities.Interprete;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/interpreti")
public class InterpreteController {

    @Autowired
    private InterpreteBusiness interpreteBusiness;

    @GetMapping(value = "/numeroTotaleInterpreti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNumeroTotaleInterpreti() {
        try {
            int numeroTotaleInterpreti = interpreteBusiness.getNumeroTotaleInterpreti();
            return ResponseEntity.ok(numeroTotaleInterpreti);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante il recupero del numero totale di interpreti", e);
        }
    }


    @GetMapping(value = "/interprete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreteById(@PathVariable Long id) throws ExternalComunicationErrorException {
        try {
            Object result = interpreteBusiness.getInterpreteResponse(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException(e.getMessage());
        }
    }

    @GetMapping(value = "/nomeOCognome")
    public ResponseEntity<?> getInterpreteByStringaA(@RequestParam(name = "nomeOCognome") String nomeOCognome) throws ExternalComunicationErrorException {
        try {
            Object result = interpreteBusiness.getInterpreteByNomeOrCognome(nomeOCognome);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException(e.getMessage());
        }
    }

    @GetMapping(value = "/listaInterpreti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Interprete>> getInterpreti() throws ExternalComunicationErrorException {

        List<Interprete> interpreti = interpreteBusiness.findAllInterpreti();
        if (interpreti.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(interpreti, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> newInterprete(@RequestBody InterpreteRequest request) throws ExternalComunicationErrorException {
        try {
            Interprete interprete = new Interprete(null, request.getNome(), request.getCognome(), null);


            return new ResponseEntity<>(interpreteBusiness.saveInterprete(interprete), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException(e.getMessage());
        }
    }
}



