package com.evan.lejo.configuration.security.response;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class MessageResponse {
    private String message;


    public MessageResponse( String message ) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage( String message ) {
        this.message = message;
    }
}
