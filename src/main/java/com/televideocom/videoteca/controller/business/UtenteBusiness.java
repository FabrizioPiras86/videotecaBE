package com.televideocom.videoteca.controller.business;

import com.televideocom.videoteca.Service.UtenteServiceInterfaccia;
import com.televideocom.videoteca.controller.pojo.UtenteRegistrazione;
import com.televideocom.videoteca.entities.Utente;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;

@Component
public class UtenteBusiness {

    @Autowired
    private UtenteServiceInterfaccia utenteServiceInterfaccia;

    public int getNumeroTotaleMembri() throws ExternalComunicationErrorException {
        try {
            List<Utente> utenteList = utenteServiceInterfaccia.getFindAll();
            return utenteList.size();
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante il recupero del numero totale di membri", e);
        }
    }


    public Utente getFindByEmail(String email) {
        return utenteServiceInterfaccia.getFindByEmail(email);
    }

    public List<Utente> getFindByBirthYear(Integer year) {  // Updated to Integer
        return utenteServiceInterfaccia.getFindByBirthYear(year);
    }

    public Utente delete(Long userId) {
        Utente utente = utenteServiceInterfaccia.getUserById(userId);
        Utente utenteCancellato = new Utente(
                utente.getIdUtente(),
                utente.getNome(),
                utente.getCognome(),
                utente.getDdn(),
                utente.getEmail(),
                utente.getUsername(),
                utente.getPassword(),
                utente.getAutorizzazione()
        );
        if (utente != null) {
            utenteServiceInterfaccia.delete(utente);
            return utenteCancellato;
        } else {
            throw new EntityNotFoundException("Utente non trovato con id: " + userId);
        }
    }

    public List<Utente> getFindByNome(String nome) {return utenteServiceInterfaccia.getFindByNome(nome);}

    public List<Utente> getFindByCognome(String cognome) {
        return utenteServiceInterfaccia.getFindByCognome(cognome);
    }

    public Utente getFindByUsername(String username) {
        return utenteServiceInterfaccia.getFindByUsername(username);
    }

    public Utente getUserById(Long userId) {
        return utenteServiceInterfaccia.getUserById(userId);
    }

    public Utente updateUserData(String username, Utente updatedUserData) {
        Utente existingUser = utenteServiceInterfaccia.getFindByUsername(username);

        if (existingUser == null) {
            return null;
        }

        existingUser.setNome(updatedUserData.getNome());
        existingUser.setCognome(updatedUserData.getCognome());
        existingUser.setDdn(updatedUserData.getDdn());
        existingUser.setEmail(updatedUserData.getEmail());

        return utenteServiceInterfaccia.saveUser(existingUser);
    }

    public Utente updateAdmin(String username, Utente updatedUserData) {
        Utente existingUser = utenteServiceInterfaccia.getFindByUsername(username);

        if (existingUser == null) {
            return null;
        }

        existingUser.setNome(updatedUserData.getNome());
        existingUser.setCognome(updatedUserData.getCognome());
        existingUser.setDdn(updatedUserData.getDdn());
        existingUser.setEmail(updatedUserData.getEmail());
        existingUser.setAutorizzazione(updatedUserData.getAutorizzazione());

        return utenteServiceInterfaccia.saveUser(existingUser);
    }

    public Utente saveUser(UtenteRegistrazione utente) {
        return utenteServiceInterfaccia.saveUser(new Utente(
                null,
                null,
                null,
                null,
                null,
                utente.getUsername(),
                utente.getPassword(),
                10L
        ));
    }

    public Object test(UtenteRegistrazione utente) {
        Utente user =  utenteServiceInterfaccia.getFindByUsername(utente.getUsername());
        String token = UUID.randomUUID().toString();
        record r(String token, String username, Long auth) {}
        return new r(token, user.getUsername(), user.getAutorizzazione());
    }


}
