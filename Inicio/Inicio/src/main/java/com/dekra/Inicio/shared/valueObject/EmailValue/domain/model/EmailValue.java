package com.dekra.Inicio.shared.valueObject.EmailValue.domain.model;

import lombok.Data;

@Data
public final class EmailValue {

    private final String value;


    public EmailValue(String value) {

        this.validate(value);
        this.value = value;

    }


    public static EmailValue of(String value) {
        return new EmailValue(value);
    }


    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }

    }



}
