package com.grupo10.user_service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void constructor_ShouldCreateExceptionWithMessage() {
        // Given
        String errorMessage = "Usuario no encontrado";

        // When
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Then
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void exception_ShouldBeThrowable() {
        // Given
        String errorMessage = "Recurso no encontrado";

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException(errorMessage);
        });
    }
}
