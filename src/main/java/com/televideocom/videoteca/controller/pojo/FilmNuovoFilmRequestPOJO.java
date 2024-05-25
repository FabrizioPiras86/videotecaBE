package com.televideocom.videoteca.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmNuovoFilmRequestPOJO {
    private String titolo;
    private String anno;
    private Long idGenere;
    private List<Long> idInterpreti;
}
