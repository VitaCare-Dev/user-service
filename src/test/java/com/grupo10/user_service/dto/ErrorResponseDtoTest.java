package com.grupo10.user_service.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDtoTest {

    @Test
    void testGettersAndSetters() {
        // Given
        ErrorResponseDto dto = new ErrorResponseDto();
        String message = "Error de prueba";
        int status = 404;
        LocalDateTime timestamp = LocalDateTime.now();

        // When
        dto.setMessage(message);
        dto.setStatus(status);
        dto.setTimestamp(timestamp);

        // Then
        assertEquals(message, dto.getMessage());
        assertEquals(status, dto.getStatus());
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void testEquals() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ErrorResponseDto dto1 = new ErrorResponseDto();
        dto1.setMessage("Error");
        dto1.setStatus(404);
        dto1.setTimestamp(now);

        ErrorResponseDto dto2 = new ErrorResponseDto();
        dto2.setMessage("Error");
        dto2.setStatus(404);
        dto2.setTimestamp(now);

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto1);
    }

    @Test
    void testHashCode() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        ErrorResponseDto dto1 = new ErrorResponseDto();
        dto1.setMessage("Error");
        dto1.setStatus(404);
        dto1.setTimestamp(now);

        ErrorResponseDto dto2 = new ErrorResponseDto();
        dto2.setMessage("Error");
        dto2.setStatus(404);
        dto2.setTimestamp(now);

        // When & Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage("Error de prueba");
        dto.setStatus(404);
        dto.setTimestamp(LocalDateTime.now());

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("Error de prueba"));
        assertTrue(toString.contains("404"));
    }
}
