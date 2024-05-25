package com.televideocom.videoteca.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpreteRequest {
    private String nome;
    private String cognome;
}
