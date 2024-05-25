package com.televideocom.videoteca.errors;

import lombok.Data;

@Data
public class NotValidException extends Exception {
    private String message = "Informazione inserita non valida";

    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
        this.message= message;
    }

    public NotValidException(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }
}
