package com.televideocom.videoteca.repository;

import com.televideocom.videoteca.entities.Genere;
import com.televideocom.videoteca.entities.Interprete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenereRepository extends JpaRepository<Genere, Long> {

    List<Genere> findByDescrizioneContainsIgnoreCase(String descrizione);
}





