package com.grupo10.user_service.exception;

import com.grupo10.user_service.dto.ErrorResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleResourceNotFound_ShouldReturnNotFoundResponse() {
        // Given
        String errorMessage = "Usuario no encontrado";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // When
        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleResourceNotFound(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void handleDuplicateResource_ShouldReturnConflictResponse() {
        // Given
        String errorMessage = "El correo ya está registrado";
        DuplicateResourceException exception = new DuplicateResourceException(errorMessage);

        // When
        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleDuplicateResource(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(HttpStatus.CONFLICT.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void handleInvalidCredentialsException_ShouldReturnUnauthorizedResponse() {
        // Given
        String errorMessage = "Credenciales inválidas";
        InvalidCredentialsException exception = new InvalidCredentialsException(errorMessage);

        // When
        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleInvalidCredentialsException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getTimestamp());
    }
}
