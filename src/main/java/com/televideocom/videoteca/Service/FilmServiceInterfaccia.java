package com.televideocom.videoteca.Service;

import com.televideocom.videoteca.entities.Film;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface FilmServiceInterfaccia {

    public List<Film> getFindByTitolo(String titolo);

    public List<Film> getFindByAnno(String anno);

    public List<Film> getFindAll();

    public Optional<Film> getFindById(Long id);

    public List<Film> getByLikedinterpreteNomeContainingOrLikedinterpreteCognomeContaining(String nome, String cognome);

    public void delete(Long id);

    public Film save (Film film);

}
