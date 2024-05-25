package com.televideocom.videoteca.errors;

import lombok.Data;

@Data
public class ExternalComunicationErrorException extends Exception {
    private String message = "Accesso negato";

    public ExternalComunicationErrorException() {
        super();
    }

    public ExternalComunicationErrorException(String message) {
        super(message);
        this.message= message;
    }

    public ExternalComunicationErrorException(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }
}

