package com.triview.demo.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Holds error information from Validators.
 *
 * @author Ted Bergeron
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource extends ErrorResource {

    private String field;
    private Object rejectedValue;

    public FieldErrorResource() {
        super();
    }

    public FieldErrorResource(String resource, String field, String code, String message) {
        super(resource, code, message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    /**
     * Sets the rejected value, while checking for unsafe Strings and sanitizing them.
     *
     * @param rejectedValue the rejected value
     */
    public void setRejectedValue(Object rejectedValue) {
        Object value = rejectedValue;
        if (rejectedValue instanceof String) {
            String unsafe = (String) rejectedValue;
            value = Jsoup.clean(unsafe, Whitelist.basic());
        }
        this.rejectedValue = value;
    }
}
