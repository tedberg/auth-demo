package com.triview.demo.model.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * An ApiError captures information about errors in a consistent manner that can easily be communicated to clients.
 *
 * @author Ted Bergeron
 */
public class ApiError {
    private static final Logger log = LoggerFactory.getLogger(ApiError.class);

    private String code;
    private String message;
    private String objectName;
    private String path;
    private List<ErrorResource> globalErrors;
    private List<FieldErrorResource> fieldErrors;

    public ApiError() {
    }

    public ApiError(Integer code, String message) {
        this.code = String.valueOf(code);
        this.message = message;
    }

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError(Errors errors) {
        this.objectName = errors.getObjectName();
        this.path = errors.getNestedPath();

        if (log.isDebugEnabled()) {
            log.debug("hasGlobalErrors() = {}", errors.hasGlobalErrors());
            log.debug("getGlobalErrors() = {}", errors.getGlobalErrors());

            log.debug("hasFieldErrors() = {}", errors.hasFieldErrors());
            log.debug("getFieldErrors() = {}", errors.getFieldErrors());

            log.debug("getErrorCount() = {}", errors.getErrorCount());
            log.debug("getAllErrors() = {}", errors.getAllErrors());
        }

        if (errors.hasGlobalErrors()) {
            this.globalErrors = new ArrayList<>();

            List<ObjectError> springGlobalErrors = errors.getGlobalErrors();
            for (ObjectError objectError : springGlobalErrors) {
                ErrorResource errorResource = new ErrorResource();
                errorResource.setResource(objectError.getObjectName());
                errorResource.setCode(objectError.getCode());
                errorResource.setMessage(objectError.getDefaultMessage());
                this.globalErrors.add(errorResource);
            }
        }

        if (errors.hasFieldErrors()) {
            this.fieldErrors = new ArrayList<>();

            List<FieldError> springFieldErrors = errors.getFieldErrors();
            for (FieldError fieldError : springFieldErrors) {
                FieldErrorResource fieldErrorResource = new FieldErrorResource();
                fieldErrorResource.setResource(fieldError.getObjectName());
                fieldErrorResource.setField(fieldError.getField());
                fieldErrorResource.setRejectedValue(fieldError.getRejectedValue());
                fieldErrorResource.setCode(fieldError.getCode());
                fieldErrorResource.setMessage(fieldError.getDefaultMessage());
                this.fieldErrors.add(fieldErrorResource);
            }
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<ErrorResource> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<ErrorResource> globalErrors) {
        this.globalErrors = globalErrors;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<FieldErrorResource> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}
