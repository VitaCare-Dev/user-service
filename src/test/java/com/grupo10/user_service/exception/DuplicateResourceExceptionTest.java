package com.grupo10.user_service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateResourceExceptionTest {

    @Test
    void constructor_ShouldCreateExceptionWithMessage() {
        // Given
        String errorMessage = "El correo ya está registrado";

        // When
        DuplicateResourceException exception = new DuplicateResourceException(errorMessage);

        // Then
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void exception_ShouldBeThrowable() {
        // Given
        String errorMessage = "Recurso duplicado";

        // When & Then
        assertThrows(DuplicateResourceException.class, () -> {
            throw new DuplicateResourceException(errorMessage);
        });
    }
}
