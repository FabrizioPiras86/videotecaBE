package com.televideocom.videoteca.controller.pojo;

import com.televideocom.videoteca.entities.Film;
import com.televideocom.videoteca.entities.Interprete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmInterpreteUpdateResponsePOJO {

    private Film film;
    private List<Interprete> interpreti;
}
