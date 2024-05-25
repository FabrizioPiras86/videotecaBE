package com.televideocom.videoteca.controller.business;

import com.televideocom.videoteca.Service.InterpreteServiceInterfaccia;
import com.televideocom.videoteca.entities.Interprete;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InterpreteBusiness {

    @Autowired
    private InterpreteServiceInterfaccia interpreteServiceInterfaccia;

    public int getNumeroTotaleInterpreti() throws ExternalComunicationErrorException {
        try {
            List<Interprete> interpreteList = interpreteServiceInterfaccia.getFindAll();
            return interpreteList.size();
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante il recupero del numero totale di interpreti", e);
        }
    }


    public List<Interprete> findAllInterpreti() {

        return interpreteServiceInterfaccia.getFindAll();
    }

    public Object getInterpreteByNomeOrCognome(String nomeOCognome) throws ExternalComunicationErrorException {
        if (nomeOCognome == null || nomeOCognome.length() < 3) {
            throw new IllegalArgumentException("La stringa deve contenere almeno 3 caratteri");
        }

        List<Interprete> interpreti =  interpreteServiceInterfaccia.getNomeOCognome(nomeOCognome, nomeOCognome);
        if (interpreti.isEmpty()) {
            throw new ExternalComunicationErrorException("Interprete non trovato");
        }

        return interpreti;
    }

    public Object getInterpreteResponse(Long id) throws ExternalComunicationErrorException {
        if (id == null) {
            throw new IllegalArgumentException("ID non fornito");
        }

        Optional<Interprete> optionalInterprete = interpreteServiceInterfaccia.getFindById(id);
        if (optionalInterprete.isEmpty()) {
            throw new ExternalComunicationErrorException("Interprete non trovato");
        }

        return optionalInterprete.get();
    }

    public Interprete saveInterprete(Interprete interprete) throws ExternalComunicationErrorException {
        if (interprete == null || interprete.getNome() == null || interprete.getNome().isEmpty() ||
                interprete.getCognome() == null || interprete.getCognome().isEmpty()) {
            throw new IllegalArgumentException("Nome o cognome dell'interprete mancante");
        }

        try {
            interprete.setIdInterprete(null);
            return interpreteServiceInterfaccia.save(interprete);
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante il salvataggio dell'interprete");
        }
    }
}
