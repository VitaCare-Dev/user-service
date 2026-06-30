package com.grupo10.user_service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDtoTest {

    @Test
    void testGettersAndSetters() {
        // Given
        UserRequestDto dto = new UserRequestDto();
        String correo = "test@example.com";
        String firebaseUid = "firebase-uid-123";
        String rol = "USER";

        // When
        dto.setCorreo(correo);
        dto.setFirebaseUid(firebaseUid);
        dto.setRol(rol);

        // Then
        assertEquals(correo, dto.getCorreo());
        assertEquals(firebaseUid, dto.getFirebaseUid());
        assertEquals(rol, dto.getRol());
    }

    @Test
    void testEquals() {
        // Given
        UserRequestDto dto1 = new UserRequestDto();
        dto1.setCorreo("test@example.com");
        dto1.setFirebaseUid("firebase-uid-123");
        dto1.setRol("USER");

        UserRequestDto dto2 = new UserRequestDto();
        dto2.setCorreo("test@example.com");
        dto2.setFirebaseUid("firebase-uid-123");
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
        dto1.setFirebaseUid("firebase-uid-123");

        UserRequestDto dto2 = new UserRequestDto();
        dto2.setCorreo("test@example.com");
        dto2.setFirebaseUid("firebase-uid-123");

        // When & Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        UserRequestDto dto = new UserRequestDto();
        dto.setCorreo("test@example.com");
        dto.setFirebaseUid("firebase-uid-123");

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("test@example.com"));
    }
}
