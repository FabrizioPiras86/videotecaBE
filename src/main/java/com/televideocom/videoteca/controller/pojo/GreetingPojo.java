package com.televideocom.videoteca.controller.pojo;

/**
 * Creo una classe GreetingPojo che verrà richiamata nel controller
 */


public class GreetingPojo {

    private String description;

    public GreetingPojo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

