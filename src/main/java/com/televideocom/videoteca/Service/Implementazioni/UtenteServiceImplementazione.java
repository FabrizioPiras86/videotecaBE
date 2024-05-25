package com.televideocom.videoteca.Service.Implementazioni;

import com.televideocom.videoteca.Service.UtenteServiceInterfaccia;
import com.televideocom.videoteca.entities.Utente;
import com.televideocom.videoteca.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UtenteServiceImplementazione implements UtenteServiceInterfaccia {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public List<Utente> getFindByBirthYear(Integer year) {  // Updated to Integer
        return utenteRepository.findByBirthYear(year);
    }

    @Override
    public List<Utente> getFindByNome(String nome) {return utenteRepository.findByNome(nome); }

    @Override
    public List<Utente> getFindByCognome(String cognome) {
        return utenteRepository.findByCognome(cognome);
    }

    @Override
    public Utente getFindByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
    @Override
    public Utente getFindByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    @Override
    public Utente saveUser(Utente user) {
        return utenteRepository.save(user);
    }

    @Override
    public Utente getUserById(Long userId) {
        return utenteRepository.findByIdUtente(userId);
    }

    @Override
    public void delete(Utente utente) {
        utenteRepository.delete(utente);
    }

    @Override
    public List<Utente> getFindAll() {
        return utenteRepository.findAll();
    }


}
