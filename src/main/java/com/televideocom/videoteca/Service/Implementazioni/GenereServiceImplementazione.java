package com.televideocom.videoteca.Service.Implementazioni;

import com.televideocom.videoteca.Service.GenereServiceInterfaccia;
import com.televideocom.videoteca.entities.Genere;
import com.televideocom.videoteca.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenereServiceImplementazione implements GenereServiceInterfaccia {

    @Autowired
    private GenereRepository genereRepository;

    @Override
    public List<Genere> getDescrizione(String descrizione) {
        return genereRepository.findByDescrizioneContainsIgnoreCase(descrizione);
    }

    @Override
    public List<Genere> getFindAll() {
        return genereRepository.findAll();
    }

    @Override
    public Optional<Genere> getFindById(Long id) {
        return genereRepository.findById(id);
    }

    @Override
    public Genere save(Genere genere) {
        return genereRepository.save(genere);
    }
}
