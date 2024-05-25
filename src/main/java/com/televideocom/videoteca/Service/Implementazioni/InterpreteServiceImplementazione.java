package com.televideocom.videoteca.Service.Implementazioni;

import com.televideocom.videoteca.Service.InterpreteServiceInterfaccia;
import com.televideocom.videoteca.entities.Interprete;
import com.televideocom.videoteca.repository.InterpreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterpreteServiceImplementazione implements InterpreteServiceInterfaccia {

    @Autowired
    private InterpreteRepository interpreteRepository;


    @Override
    public List<Interprete> getNomeOCognome(String nome, String cognome) {
        return interpreteRepository.findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(nome, cognome);
    }

    @Override
    public List<Interprete> getFindAll() {
        return interpreteRepository.findAll();
    }

    @Override
    public Optional<Interprete> getFindById(Long id) {

        return interpreteRepository.findById(id);
    }

    @Override
    public List<Interprete> getFindAllById(List<Long> id) {
        return interpreteRepository.findAllById(id);
    }

    @Override
    public Interprete save(Interprete interprete) {
        return interpreteRepository.save(interprete);
    }
}
