package com.televideocom.videoteca.controller.pojo;

import com.televideocom.videoteca.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDeleteResponsePOJO {

    private String message;
    private Film deletedFilm;

}
