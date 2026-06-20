package com.grupo10.user_service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDtoTest {

    @Test
    void testGettersAndSetters() {
        // Given
        UserRequestDto dto = new UserRequestDto();
        String correo = "test@example.com";
        String password = "password123";
        String rol = "USER";

        // When
        dto.setCorreo(correo);
        dto.setPassword(password);
        dto.setRol(rol);

        // Then
        assertEquals(correo, dto.getCorreo());
        assertEquals(password, dto.getPassword());
        assertEquals(rol, dto.getRol());
    }

    @Test
    void testEquals() {
        // Given
        UserRequestDto dto1 = new UserRequestDto();
        dto1.setCorreo("test@example.com");
        dto1.setPassword("password123");
        dto1.setRol("USER");

        UserRequestDto dto2 = new UserRequestDto();
        dto2.setCorreo("test@example.com");
        dto2.setPassword("password123");
        dto2.setRol("USER");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto1);
    }

    @Test
    void testHashCode() {
        // Given
        UserRequestDto dto1 = new UserRequestDto();
        dto1.setCorreo("test@example.com");
        dto1.setPassword("password123");

        UserRequestDto dto2 = new UserRequestDto();
        dto2.setCorreo("test@example.com");
        dto2.setPassword("password123");

        // When & Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        UserRequestDto dto = new UserRequestDto();
        dto.setCorreo("test@example.com");
        dto.setPassword("password123");

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("test@example.com"));
    }
}
