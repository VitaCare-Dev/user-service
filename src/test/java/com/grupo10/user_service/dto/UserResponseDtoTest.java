package com.grupo10.user_service.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    void testGettersAndSetters() {
        // Given
        UserResponseDto dto = new UserResponseDto();
        Long id = 1L;
        String correo = "test@example.com";
        String rol = "USER";
        int activo = 1;
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        dto.setId(id);
        dto.setCorreo(correo);
        dto.setRol(rol);
        dto.setActivo(activo);
        dto.setCreatedAt(createdAt);

        // Then
        assertEquals(id, dto.getId());
        assertEquals(correo, dto.getCorreo());
        assertEquals(rol, dto.getRol());
        assertEquals(activo, dto.getActivo());
        assertEquals(createdAt, dto.getCreatedAt());
    }

    @Test
    void testEquals() {
        // Given
        UserResponseDto dto1 = new UserResponseDto();
        dto1.setId(1L);
        dto1.setCorreo("test@example.com");
        dto1.setRol("USER");
        dto1.setActivo(1);

        UserResponseDto dto2 = new UserResponseDto();
        dto2.setId(1L);
        dto2.setCorreo("test@example.com");
        dto2.setRol("USER");
        dto2.setActivo(1);

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto1);
    }

    @Test
    void testHashCode() {
        // Given
        UserResponseDto dto1 = new UserResponseDto();
        dto1.setId(1L);
        dto1.setCorreo("test@example.com");

        UserResponseDto dto2 = new UserResponseDto();
        dto2.setId(1L);
        dto2.setCorreo("test@example.com");

        // When & Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        UserResponseDto dto = new UserResponseDto();
        dto.setId(1L);
        dto.setCorreo("test@example.com");

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("test@example.com"));
    }
}
