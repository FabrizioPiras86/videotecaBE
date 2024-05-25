package com.televideocom.videoteca.repository;

import com.televideocom.videoteca.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

        Utente findByUsername(String username);
        List<Utente> findByNome(String nome);
        List<Utente> findByCognome(String cognome);
        Utente findByEmail(String email);
        Utente findByIdUtente(Long idUtente);
        @Query("SELECT u FROM Utente u WHERE FUNCTION('YEAR', u.ddn) = :year")
        List<Utente> findByBirthYear(@Param("year") Integer year);
    }



