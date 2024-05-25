package com.televideocom.videoteca.controller.business;

import com.televideocom.videoteca.Service.GenereServiceInterfaccia;
import com.televideocom.videoteca.entities.Genere;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GeneriBusiness {

    @Autowired
    private GenereServiceInterfaccia genereServiceInterfaccia;

    public int getNumeroTotaleGeneri() throws ExternalComunicationErrorException {
        try {
            List<Genere> generiList = genereServiceInterfaccia.getFindAll();
            return generiList.size();
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante il recupero del numero totale di generi", e);
        }
    }



    public Optional<Genere> getFindById(Long id) {
        return genereServiceInterfaccia.getFindById(id);
    }

    public List<Genere> findAllGeneri() {
        return genereServiceInterfaccia.getFindAll();
    }

    public Object getGenereByDescrizione(String descrizione) throws ExternalComunicationErrorException {
        if (descrizione.length() < 3) {
            throw new IllegalArgumentException("La descrizione deve contenere almeno 3 caratteri");
        }

        List<Genere> generi = genereServiceInterfaccia.getDescrizione(descrizione);
        if (generi.isEmpty()) {
            throw new ExternalComunicationErrorException("Genere non trovato");
        }

        return generi;
    }

    public Genere saveGenere(Genere genere) throws ExternalComunicationErrorException {
        if (genere == null || genere.getDescrizione() == null || genere.getDescrizione().isEmpty()) {
            throw new IllegalArgumentException("Descrizione mancante");
        }

        try {
            genere.setIdGenere(null);
            return genereServiceInterfaccia.save(genere);
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante il salvataggio del genere");
        }
    }

}
