package com.devsuperior.dscliente.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    List<FieldError> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String fieldMessage) {
        errors.add(new FieldError(fieldName, fieldMessage));
    }
}
