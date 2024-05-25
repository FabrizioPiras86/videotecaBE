package com.televideocom.videoteca.errors;

import lombok.Data;

@Data
public class UnauthorizedException extends Exception {
    private String message = "Elemento ricercato non trovato";

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
        this.message= message;
    }

    public UnauthorizedException(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }

}
