package com.televideocom.videoteca.Service;

import com.televideocom.videoteca.entities.Genere;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GenereServiceInterfaccia {

    public List<Genere> getDescrizione(String descrizione);

    public List<Genere> getFindAll();

    public Optional<Genere> getFindById(Long id);

    public Genere save(Genere genere);

}
