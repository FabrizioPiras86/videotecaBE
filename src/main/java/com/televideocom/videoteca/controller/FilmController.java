package com.televideocom.videoteca.controller;

import com.televideocom.videoteca.controller.business.FilmBusiness;
import com.televideocom.videoteca.controller.pojo.*;
import com.televideocom.videoteca.entities.Film;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmBusiness filmBusiness;

    @GetMapping(value = "/numeroTotaleFilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNumeroTotaleFilm() {
        try {
            int numeroTotaleFilm = filmBusiness.getNumeroTotaleFilm();
            return ResponseEntity.ok(numeroTotaleFilm);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante il recupero del numero totale di film", e);
        }
    }


    @GetMapping(value = "/numfilmpergenere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNumeroFilmPerGenere() {
        try {
            Map<String, Integer> filmPerGenereMap = filmBusiness.getNumeroFilmPerGenere();
            return ResponseEntity.ok(filmPerGenereMap);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante il calcolo del numero di film per genere", e);
        }
    }

    @GetMapping(value = "/numfilmperinterprete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNumeroFilmPerInterprete() {
        try {
            Map<String, Integer> filmPerInterpreteMap = filmBusiness.getNumeroFilmPerInterprete();
            return ResponseEntity.ok(filmPerInterpreteMap);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante il calcolo del numero di film per interprete", e);
        }
    }

    @GetMapping(value = "/titles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmTitles() {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmTitles = filmBusiness.getListaTitoli();
            return ResponseEntity.ok(filmTitles);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmTitleById(@PathVariable("id") Long id) {
        try {
            FilmTitoloAnnoGenereInterpretiPOJO film = filmBusiness.getTitoloById(id);
            if (film != null) {
                return ResponseEntity.ok(film);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }


    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmByTitle(@RequestParam("titolo") String titolo) {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmTitles = filmBusiness.getTitoloByNome(titolo);
            return ResponseEntity.ok(filmTitles);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }


    @GetMapping(value = "/anno", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmByYear(@RequestParam("anno") String anno) {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmTitles = filmBusiness.getTitoloByAnno(anno);
            return ResponseEntity.ok(filmTitles);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }

    @GetMapping(value = "/genere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmByGenre(@RequestParam("genere") String genere) {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmList = filmBusiness.getTitoloByGenere(genere);
            return ResponseEntity.ok(filmList);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }

    @GetMapping(value = "/attori", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmByActor(@RequestParam("filter") String filter) {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> films = filmBusiness.getTitoloByInterprete(filter);
            return ResponseEntity.ok(films);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }


    @GetMapping(value = "/titolocompleto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListaTitoliCompleta() {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> films = filmBusiness.getListaTitoliCompleta();
            return ResponseEntity.ok(films);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }

    @GetMapping(value = "/titolocompleto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTitoloCompletoById(@PathVariable("id") Long id) {
        try {
            FilmTitoloAnnoGenereInterpretiPOJO film = filmBusiness.getTitoloCompletoById(id);
            if (film != null) {
                return ResponseEntity.ok(film);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la comunicazione con il repository di film", e);
        }
    }

    @PostMapping(value = "/nuovofilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> nuovoFilm(@RequestBody FilmNuovoFilmRequestPOJO nuovoFilmRequest) {
        try {
            FilmTitoloAnnoGenereInterpretiPOJO nuovoFilmResponse = filmBusiness.nuovoFilm(nuovoFilmRequest);
            return ResponseEntity.ok(nuovoFilmResponse);
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante l'inserimento del nuovo film", e);
        }
    }

    @PostMapping(value = "/modificafilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFilm(@RequestParam(value = "id", required = true) Long id,
                                        @RequestParam(value = "titolo", required = false) String titolo,
                                        @RequestParam(value = "anno", required = false) String anno) {
        try {
            Film updatedFilm = filmBusiness.updateFilm(id, titolo, anno);
            if (updatedFilm != null) {
                return ResponseEntity.ok(updatedFilm);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del film", e);
        }
    }

    @PostMapping(value = "/updategenerefilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFilmGenere(@RequestParam(value = "id_film", required = true) Long idFilm,
                                              @RequestParam(value = "id_genere", required = true) Long idGenere) {
        try {
            FilmGenereUpdateResponsePOJO response = filmBusiness.updateFilmGenere(idFilm, idGenere);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del genere del film", e);
        }
    }

    @DeleteMapping(value = "/deletefilm/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFilm(@PathVariable("id") Long id) {
        try {
            FilmDeleteResponsePOJO response = filmBusiness.deleteFilm(id);
            if (response != null) {
                // Carica esplicitamente la collezione likedInterprete prima di restituire la risposta
                response.getDeletedFilm().getLikedInterprete().size();
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione del film", e);
        }
    }



    @PostMapping(value = "/aggiungiattorefilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> aggiungiAttoreFilm(@RequestParam Long id_film, @RequestParam Long id_interprete) {
        try {
            FilmInterpreteUpdateResponsePOJO response = filmBusiness.aggiungiAttoreFilm(id_film, id_interprete);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante l'aggiunta dell'attore al film", e);
        }
    }


    @PostMapping(value = "/rimuoviattorefilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rimuoviAttoreFilm(@RequestParam Long idFilm, @RequestParam Long idInterprete) {
        try {
            FilmInterpreteUpdateResponsePOJO response = filmBusiness.rimuoviAttoreFilm(idFilm, idInterprete);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExternalComunicationErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la rimozione dell'attore dal film", e);
        }
    }

}


