package com.televideocom.videoteca.repository;

import com.televideocom.videoteca.entities.Interprete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterpreteRepository extends JpaRepository<Interprete, Long> {

    List<Interprete> findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(String nome, String cognome);

}





