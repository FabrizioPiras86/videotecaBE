package com.televideocom.videoteca.errors;

import lombok.Data;

@Data
public class ForbiddenException extends Exception {
    private String message = "Accesso negato";

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
        this.message= message;
    }

    public ForbiddenException(String message, Throwable innerException){
        super(message, innerException);
        this.message= message;
    }
}