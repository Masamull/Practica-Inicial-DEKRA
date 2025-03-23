package com.dekra.Inicio.sharedTests;

import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailValueTest {

    @Test
    public void testValidEmail() {
        EmailValue email = new EmailValue("user@example.com");
        assertEquals("user@example.com", email.getValue());
    }
}