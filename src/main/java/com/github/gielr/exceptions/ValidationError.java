package com.github.gielr.exceptions;

public class ValidationError {
    private String field;
    private String description;

    public ValidationError() {
    }

    public ValidationError(String field, String description) {
        this.field = field;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("{\"field\":\"%s\"," + "\"description\":\"%s\"}", field, description);
    }

    public String getDescription() {
        return description;
    }

    public String getField() {
        return field;
    }
}