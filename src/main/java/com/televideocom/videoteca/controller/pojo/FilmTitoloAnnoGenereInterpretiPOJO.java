package com.televideocom.videoteca.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmTitoloAnnoGenereInterpretiPOJO {

    private Long idFilm;
    private String titolo;
    private String anno;
    private String genere;
    private List<String> interpreti;

}

