package com.televideocom.videoteca.controller;

import com.televideocom.videoteca.controller.business.UtenteBusiness;
import com.televideocom.videoteca.controller.pojo.UtenteRegistrazione;
import com.televideocom.videoteca.entities.Utente;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import com.televideocom.videoteca.security.CustomPasswordEncoder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    @Autowired
    private UtenteBusiness utenteBusiness;

    @GetMapping(value = "/numeroTotaleMembri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNumeroTotaleMembri() {
        try {
            int numeroTotaleMembri = utenteBusiness.getNumeroTotaleMembri();
            return ResponseEntity.ok(numeroTotaleMembri);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante il recupero del numero totale di membri", e);
        }
    }


    @GetMapping("/cercaUsername")
    public ResponseEntity<?> searchUtenteByUsername(@RequestParam String username) {
        Utente utente = utenteBusiness.getFindByUsername(username);
        if (utente != null) {
            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idUtente}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUtente) {
        try {
            Utente deletedUser = utenteBusiness.delete(idUtente);
            return ResponseEntity.ok(deletedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cercaEmail")
    public ResponseEntity<?> searchUtenteByEmail(@RequestParam String email) {
        Utente utente = utenteBusiness.getFindByEmail(email);
        if (utente != null) {
            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cercaDdn")
    public ResponseEntity<List<Utente>> searchUtenteByBirthYear(@RequestParam Integer year) {  // Updated to Integer
        List<Utente> utenti = utenteBusiness.getFindByBirthYear(year);
        if (utenti != null && !utenti.isEmpty()) {
            return ResponseEntity.ok(utenti);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cercaNome")
    public ResponseEntity<?> searchUtenteByNome(@RequestParam String nome) {
        List<Utente> utente = utenteBusiness.getFindByNome(nome);
        if (utente != null) {
            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cercaCognome")
    public ResponseEntity<?> searchUtenteByCognome(@RequestParam String cognome) {
        List<Utente> utente = utenteBusiness.getFindByCognome(cognome);
        if (utente != null) {
            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{username}/update")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody Utente updatedUserData) {
        Utente updatedUser = utenteBusiness.updateUserData(username, updatedUserData);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}/updateAdmin")
    public ResponseEntity<?> updateUserAdmin(@PathVariable String username, @RequestBody Utente updatedUserData) {
        Utente updatedUser = utenteBusiness.updateAdmin(username, updatedUserData);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UtenteRegistrazione utente) {
        if (utenteBusiness.getFindByUsername(utente.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        String encodedPassword = CustomPasswordEncoder.encode(utente.getPassword());
        utente.setPassword(encodedPassword);
        return new ResponseEntity<>(utenteBusiness.saveUser(utente), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UtenteRegistrazione utente) {
        if (utente != null) {
            // Assuming utenteBusiness.test(utente) returns some kind of response object
            return new ResponseEntity<>(utenteBusiness.test(utente), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

