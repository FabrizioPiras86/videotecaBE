package com.televideocom.videoteca.errors;

import lombok.Data;

@Data
public class NotFoundException extends Exception {
    private String message = "Elemento ricercato non trovato";

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.message= message;
    }

    public NotFoundException(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }
}
