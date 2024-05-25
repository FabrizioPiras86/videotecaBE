package com.televideocom.videoteca.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmTitoloAnnoPOJO {
    private Long id;
    private String titolo;
    private String anno;


}
