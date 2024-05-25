package com.televideocom.videoteca.Service;

import com.televideocom.videoteca.entities.Utente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtenteServiceInterfaccia {

    List<Utente> getFindByBirthYear(Integer year);
    List<Utente> getFindByNome(String nome);
    List<Utente> getFindByCognome(String cognome);
    Utente getFindByEmail(String email);
    Utente getFindByUsername(String username);
    Utente saveUser(Utente user);
    Utente getUserById(Long userId);
    void delete (Utente utente);
    List<Utente> getFindAll();
}
