package com.televideocom.videoteca.repository;

import com.televideocom.videoteca.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByTitoloContaining(String titolo);

    List<Film> findByAnno(String anno);

    List<Film> findByLikedInterpreteNomeContainingOrLikedInterpreteCognomeContaining(String nome, String cognome);

}
