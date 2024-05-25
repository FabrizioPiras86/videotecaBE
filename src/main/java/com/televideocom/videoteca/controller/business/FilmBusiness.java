package com.televideocom.videoteca.controller.business;

import com.televideocom.videoteca.Service.FilmServiceInterfaccia;
import com.televideocom.videoteca.Service.GenereServiceInterfaccia;
import com.televideocom.videoteca.Service.InterpreteServiceInterfaccia;
import com.televideocom.videoteca.controller.pojo.*;
import com.televideocom.videoteca.entities.Film;
import com.televideocom.videoteca.entities.Genere;
import com.televideocom.videoteca.entities.Interprete;
import com.televideocom.videoteca.errors.ExternalComunicationErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmBusiness {

    @Autowired
    private FilmServiceInterfaccia filmServiceInterfaccia;

    @Autowired
    private GenereServiceInterfaccia genereServiceInterfaccia;

    @Autowired
    private InterpreteServiceInterfaccia interpreteServiceInterfaccia;


    public int getNumeroTotaleFilm() throws ExternalComunicationErrorException {
        try {
            List<Film> filmList = filmServiceInterfaccia.getFindAll();
            return filmList.size();
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante il recupero del numero totale di film", e);
        }
    }

    public List<FilmTitoloAnnoGenereInterpretiPOJO> getListaTitoli() throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmDTOList = filmServiceInterfaccia.getFindAll()
                    .stream()
                    .map(film -> new FilmTitoloAnnoGenereInterpretiPOJO(
                            film.getIdFilm(),
                            film.getTitolo(),
                            film.getAnno(),
                            film.getGenere().getDescrizione(),
                            film.getLikedInterprete().stream().map(Interprete::getNome).collect(Collectors.toList())))
                    .collect(Collectors.toList());
            return filmDTOList;
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }


    public FilmTitoloAnnoGenereInterpretiPOJO getTitoloById(Long id) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(id);
            if (filmOptional.isPresent()) {
                Film film = filmOptional.get();
                List<String> interpreti = film.getLikedInterprete().stream().map(Interprete::getNome).collect(Collectors.toList());
                return new FilmTitoloAnnoGenereInterpretiPOJO(
                        film.getIdFilm(),
                        film.getTitolo(),
                        film.getAnno(),
                        film.getGenere().getDescrizione(),
                        interpreti
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }


    public List<FilmTitoloAnnoGenereInterpretiPOJO> getTitoloByNome(String titolo) throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmDTOList = filmServiceInterfaccia.getFindByTitolo(titolo)
                    .stream()
                    .map(film -> new FilmTitoloAnnoGenereInterpretiPOJO(
                            film.getIdFilm(),
                            film.getTitolo(),
                            film.getAnno(),
                            film.getGenere().getDescrizione(),
                            film.getLikedInterprete().stream().map(Interprete::getNome).collect(Collectors.toList())))
                    .collect(Collectors.toList());
            return filmDTOList;
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }


    public List<FilmTitoloAnnoGenereInterpretiPOJO> getTitoloByAnno(String anno) throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmDTOList = filmServiceInterfaccia.getFindByAnno(anno)
                    .stream()
                    .map(film -> new FilmTitoloAnnoGenereInterpretiPOJO(
                            film.getIdFilm(),
                            film.getTitolo(),
                            film.getAnno(),
                            film.getGenere().getDescrizione(),
                            film.getLikedInterprete().stream().map(Interprete::getNome).collect(Collectors.toList())))
                    .collect(Collectors.toList());
            return filmDTOList;
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }


    public List<FilmTitoloAnnoGenereInterpretiPOJO> getTitoloByGenere(String genereDescrizione) throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmDTOList = new ArrayList<>();
            List<Genere> generi = genereServiceInterfaccia.getDescrizione(genereDescrizione);
            for (Genere genere : generi) {
                filmDTOList.addAll(genere.getFilm().stream()
                        .map(film -> new FilmTitoloAnnoGenereInterpretiPOJO(
                                film.getIdFilm(),
                                film.getTitolo(),
                                film.getAnno(),
                                genere.getDescrizione(),
                                film.getLikedInterprete().stream().map(Interprete::getNome).collect(Collectors.toList())))
                        .collect(Collectors.toList()));
            }
            return filmDTOList;
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }


    public List<FilmTitoloAnnoGenereInterpretiPOJO> getTitoloByInterprete(String filter) throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmDTOList = new ArrayList<>();
            List<Film> films = filmServiceInterfaccia.getByLikedinterpreteNomeContainingOrLikedinterpreteCognomeContaining(filter, filter);
            for (Film film : films) {
                List<String> interpreti = film.getLikedInterprete().stream()
                        .map(interprete -> interprete.getNome() + " " + interprete.getCognome())
                        .collect(Collectors.toList());

                filmDTOList.add(new FilmTitoloAnnoGenereInterpretiPOJO(
                        film.getIdFilm(),
                        film.getTitolo(),
                        film.getAnno(),
                        film.getGenere().getDescrizione(),
                        interpreti
                ));
            }
            return filmDTOList;
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }


    public List<FilmTitoloAnnoGenereInterpretiPOJO> getListaTitoliCompleta() throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmDTOList = new ArrayList<>();
            List<Film> films = filmServiceInterfaccia.getFindAll();

            for (Film film : films) {

                String genere = film.getGenere() != null ? film.getGenere().getDescrizione() : "N/A";

                List<String> interpreti = film.getLikedInterprete().stream()
                        .map(interprete -> interprete.getNome() + " " + interprete.getCognome())
                        .collect(Collectors.toList());

                filmDTOList.add(new FilmTitoloAnnoGenereInterpretiPOJO(
                        film.getIdFilm(),
                        film.getTitolo(),
                        film.getAnno(),
                        genere,
                        interpreti
                ));
            }
            return filmDTOList;
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }

    public FilmTitoloAnnoGenereInterpretiPOJO getTitoloCompletoById(Long id) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(id);
            if (filmOptional.isPresent()) {
                Film film = filmOptional.get();
                String genere = film.getGenere() != null ? film.getGenere().getDescrizione() : "N/A";
                List<String> interpreti = film.getLikedInterprete().stream()
                        .map(interprete -> interprete.getNome() + " " + interprete.getCognome())
                        .collect(Collectors.toList());

                return new FilmTitoloAnnoGenereInterpretiPOJO(
                        film.getIdFilm(),
                        film.getTitolo(),
                        film.getAnno(),
                        genere,
                        interpreti
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la comunicazione con il repository di film", e);
        }
    }

    public FilmTitoloAnnoGenereInterpretiPOJO nuovoFilm(FilmNuovoFilmRequestPOJO nuovoFilmRequest) throws ExternalComunicationErrorException {
        try {
            Film nuovoFilm = new Film();

            Optional<Genere> genereOptional = genereServiceInterfaccia.getFindById(nuovoFilmRequest.getIdGenere());
            if (!genereOptional.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genere non trovato");
            }


            List<Long> idInterpretiList = nuovoFilmRequest.getIdInterpreti();
            List<Interprete> interpreti = new ArrayList<>();
            for (Long idInterprete : idInterpretiList) {
                Optional<Interprete> interpreteOptional = interpreteServiceInterfaccia.getFindById(idInterprete);
                interpreteOptional.ifPresent(interpreti::add);
            }
            if (interpreti.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Interpreti non trovati");
            }
            nuovoFilm.setTitolo(nuovoFilmRequest.getTitolo());
            nuovoFilm.setAnno(nuovoFilmRequest.getAnno());
            nuovoFilm.setGenere(genereOptional.get());
            nuovoFilm.setLikedInterprete(interpreti);

            Film savedFilm = filmServiceInterfaccia.save(nuovoFilm);

            FilmTitoloAnnoGenereInterpretiPOJO nuovoFilmResponse = new FilmTitoloAnnoGenereInterpretiPOJO();
            nuovoFilmResponse.setIdFilm(savedFilm.getIdFilm());
            nuovoFilmResponse.setTitolo(savedFilm.getTitolo());
            nuovoFilmResponse.setAnno(savedFilm.getAnno());
            nuovoFilmResponse.setGenere(savedFilm.getGenere().getDescrizione());
            nuovoFilmResponse.setInterpreti(savedFilm.getLikedInterprete().stream().map(interprete -> interprete.getNome() + " " + interprete.getCognome()).collect(Collectors.toList()));

            return nuovoFilmResponse;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Errore durante l'inserimento del nuovo film", e);
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante l'inserimento del nuovo film", e);
        }
    }

    public Film updateFilm(Long id, String titolo, String anno) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(id);
            if (filmOptional.isPresent()) {
                Film film = filmOptional.get();
                if (titolo != null) {
                    film.setTitolo(titolo);
                }
                if (anno != null) {
                    film.setAnno(anno);
                }
                Film updatedFilm = filmServiceInterfaccia.save(film);
                return updatedFilm;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante l'aggiornamento del film", e);
        }
    }

    public FilmGenereUpdateResponsePOJO updateFilmGenere(Long idFilm, Long idGenere) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(idFilm);
            Optional<Genere> genereOptional = genereServiceInterfaccia.getFindById(idGenere);

            if (filmOptional.isPresent() && genereOptional.isPresent()) {
                Film film = filmOptional.get();
                Genere newGenere = genereOptional.get();
                film.setGenere(newGenere);
                Film updatedFilm = filmServiceInterfaccia.save(film);

                return new FilmGenereUpdateResponsePOJO(updatedFilm, newGenere);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante l'aggiornamento del genere del film", e);
        }
    }


    public FilmDeleteResponsePOJO deleteFilm(Long id) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(id);
            if (filmOptional.isPresent()) {
                Film film = filmOptional.get();

                // Carica esplicitamente la collezione likedInterprete
                film.getLikedInterprete().size();

                // Rimuovi i legami con gli interpreti
                film.getLikedInterprete().clear();
                // Salva il film per aggiornare il database senza i legami con gli interpreti
                filmServiceInterfaccia.save(film);

                // Ora puoi eliminare effettivamente il film
                filmServiceInterfaccia.delete(id);

                return new FilmDeleteResponsePOJO("Film eliminato con successo", film);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante l'eliminazione del film", e);
        }
    }



    public FilmInterpreteUpdateResponsePOJO aggiungiAttoreFilm(Long id_film, Long id_interprete) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(id_film);
            Optional<Interprete> interpreteOptional = interpreteServiceInterfaccia.getFindById(id_interprete);

            if (filmOptional.isPresent() && interpreteOptional.isPresent()) {
                Film film = filmOptional.get();
                Interprete attore = interpreteOptional.get();

                List<Interprete> interpreti = film.getLikedInterprete();
                if (!interpreti.contains(attore)) {
                    interpreti.add(attore);
                    film.setLikedInterprete(interpreti);
                    Film updatedFilm = filmServiceInterfaccia.save(film);

                    return new FilmInterpreteUpdateResponsePOJO(updatedFilm, interpreti);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {

            throw new ExternalComunicationErrorException("Errore durante l'aggiunta dell'attore al film", e);
        }
    }

    public FilmInterpreteUpdateResponsePOJO rimuoviAttoreFilm(Long idFilm, Long idAttore) throws ExternalComunicationErrorException {
        try {
            Optional<Film> filmOptional = filmServiceInterfaccia.getFindById(idFilm);
            Optional<Interprete> interpreteOptional = interpreteServiceInterfaccia.getFindById(idAttore);

            if (filmOptional.isPresent() && interpreteOptional.isPresent()) {
                Film film = filmOptional.get();
                Interprete attore = interpreteOptional.get();

                List<Interprete> interpreti = film.getLikedInterprete();
                if (interpreti.contains(attore)) {
                    interpreti.remove(attore);
                    film.setLikedInterprete(interpreti);
                    Film updatedFilm = filmServiceInterfaccia.save(film);

                    return new FilmInterpreteUpdateResponsePOJO(updatedFilm, interpreti);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ExternalComunicationErrorException("Errore durante la rimozione dell'attore dal film", e);
        }
    }

    public Map<String, Integer> getNumeroFilmPerGenere() throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmList = getListaTitoli();
            Map<String, Integer> filmPerGenereMap = new HashMap<>();

            for (FilmTitoloAnnoGenereInterpretiPOJO film : filmList) {
                String genere = film.getGenere();
                filmPerGenereMap.put(genere, filmPerGenereMap.getOrDefault(genere, 0) + 1);
            }

            return filmPerGenereMap;
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException("Errore durante il calcolo del numero di film per genere", e);
        }
    }

    public Map<String, Integer> getNumeroFilmPerInterprete() throws ExternalComunicationErrorException {
        try {
            List<FilmTitoloAnnoGenereInterpretiPOJO> filmList = getListaTitoli();
            Map<String, Integer> filmPerInterpreteMap = new HashMap<>();

            for (FilmTitoloAnnoGenereInterpretiPOJO film : filmList) {
                List<String> interpreti = film.getInterpreti();
                for (String interprete : interpreti) {
                    filmPerInterpreteMap.put(interprete, filmPerInterpreteMap.getOrDefault(interprete, 0) + 1);
                }
            }

            return filmPerInterpreteMap;
        } catch (ExternalComunicationErrorException e) {
            throw new ExternalComunicationErrorException("Errore durante il calcolo del numero di film per interprete", e);
        }
    }

}