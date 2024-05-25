package com.televideocom.videoteca.Service.Implementazioni;

import com.televideocom.videoteca.Service.FilmServiceInterfaccia;
import com.televideocom.videoteca.entities.Film;

import com.televideocom.videoteca.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImplementazione implements FilmServiceInterfaccia {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void delete(Long id) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isPresent()) {
            filmRepository.delete(filmOptional.get());
        } else {
            // Se il film non è presente nel database, puoi gestire l'eccezione o fare altro come ritornare un messaggio di errore
            throw new EntityNotFoundException("Film non trovato con id: " + id);
        }
    }

    @Override
    public List<Film> getFindByTitolo(String titolo) {
        return filmRepository.findByTitoloContaining(titolo);
    }

    @Override
    public List<Film> getFindByAnno(String anno) {
        return filmRepository.findByAnno(anno);
    }

    @Override
    public List<Film> getFindAll() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Film> getFindById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public List<Film> getByLikedinterpreteNomeContainingOrLikedinterpreteCognomeContaining(String nome, String cognome) {
        return filmRepository.findByLikedInterpreteNomeContainingOrLikedInterpreteCognomeContaining(nome, cognome);
    }

    @Override
    public Film save(Film film) {
        return filmRepository.save(film);
    }
}
