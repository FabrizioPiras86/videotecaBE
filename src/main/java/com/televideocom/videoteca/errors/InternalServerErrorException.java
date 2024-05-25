package com.televideocom.videoteca.errors;

public class InternalServerErrorException extends Exception {
    private String message = "Errore di connessione al Server";

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message) {
        super(message);
        this.message= message;
    }

    public InternalServerErrorException(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }
}
