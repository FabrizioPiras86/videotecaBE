package com.televideocom.videoteca.Service;

import com.televideocom.videoteca.entities.Interprete;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InterpreteServiceInterfaccia {

    public List<Interprete> getNomeOCognome(String nome, String cognome);

    public List<Interprete> getFindAll();

    public Optional<Interprete> getFindById(Long id);

    public List<Interprete> getFindAllById(List<Long> id);

    public Interprete save (Interprete interprete);

}
