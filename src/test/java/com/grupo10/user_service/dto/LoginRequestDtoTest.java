package com.grupo10.user_service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDtoTest {

    @Test
    void testGettersAndSetters() {
        // Given
        LoginRequestDto dto = new LoginRequestDto();
        String correo = "test@example.com";
        String password = "password123";

        // When
        dto.setCorreo(correo);
        dto.setPassword(password);

        // Then
        assertEquals(correo, dto.getCorreo());
        assertEquals(password, dto.getPassword());
    }

    @Test
    void testEquals() {
        // Given
        LoginRequestDto dto1 = new LoginRequestDto();
        dto1.setCorreo("test@example.com");
        dto1.setPassword("password123");

        LoginRequestDto dto2 = new LoginRequestDto();
        dto2.setCorreo("test@example.com");
        dto2.setPassword("password123");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto1);
    }

    @Test
    void testHashCode() {
        // Given
        LoginRequestDto dto1 = new LoginRequestDto();
        dto1.setCorreo("test@example.com");
        dto1.setPassword("password123");

        LoginRequestDto dto2 = new LoginRequestDto();
        dto2.setCorreo("test@example.com");
        dto2.setPassword("password123");

        // When & Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        LoginRequestDto dto = new LoginRequestDto();
        dto.setCorreo("test@example.com");
        dto.setPassword("password123");

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("test@example.com"));
    }
}
