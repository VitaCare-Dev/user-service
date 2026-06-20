package com.grupo10.user_service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionTest {

    @Test
    void constructor_ShouldCreateExceptionWithMessage() {
        // Given
        String errorMessage = "Credenciales inválidas";

        // When
        InvalidCredentialsException exception = new InvalidCredentialsException(errorMessage);

        // Then
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void exception_ShouldBeThrowable() {
        // Given
        String errorMessage = "Contraseña incorrecta";

        // When & Then
        assertThrows(InvalidCredentialsException.class, () -> {
            throw new InvalidCredentialsException(errorMessage);
        });
    }
}
