package com.triview.demo.model.error;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Holds field error information from Validators.
 *
 * @author Ted Bergeron
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
    private String resource;
    private String code;
    private String message;

    public ErrorResource() {
    }

    public ErrorResource(String resource, String code, String message) {
        this.resource = resource;
        this.code = code;
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
