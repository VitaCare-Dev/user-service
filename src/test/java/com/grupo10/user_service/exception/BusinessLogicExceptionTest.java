package com.grupo10.user_service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessLogicExceptionTest {

    @Test
    void constructor_ShouldCreateExceptionWithMessage() {
        // Given
        String errorMessage = "Error en la lógica de negocio";

        // When
        BusinessLogicException exception = new BusinessLogicException(errorMessage);

        // Then
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void exception_ShouldBeThrowable() {
        // Given
        String errorMessage = "Operación no permitida";

        // When & Then
        assertThrows(BusinessLogicException.class, () -> {
            throw new BusinessLogicException(errorMessage);
        });
    }
}
