package com.televideocom.videoteca.controller.pojo;

import com.televideocom.videoteca.entities.Film;
import com.televideocom.videoteca.entities.Genere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmGenereUpdateResponsePOJO {

    private Film film;
    private Genere genere;

}
