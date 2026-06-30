package com.grupo10.user_service.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGettersAndSetters() {
        // Given
        User user = new User();
        Long id = 1L;
        String correo = "test@example.com";
        String firebaseUid = "firebase-uid-123";
        String rol = "USER";
        int activo = 1;
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        user.setId(id);
        user.setCorreo(correo);
        user.setFirebaseUid(firebaseUid);
        user.setRol(rol);
        user.setActivo(activo);
        user.setCreatedAt(createdAt);

        // Then
        assertEquals(id, user.getId());
        assertEquals(correo, user.getCorreo());
        assertEquals(firebaseUid, user.getFirebaseUid());
        assertEquals(rol, user.getRol());
        assertEquals(activo, user.getActivo());
        assertEquals(createdAt, user.getCreatedAt());
    }

    @Test
    void testEquals() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        user1.setCorreo("test@example.com");
        user1.setFirebaseUid("firebase-uid-123");
        user1.setRol("USER");
        user1.setActivo(1);

        User user2 = new User();
        user2.setId(1L);
        user2.setCorreo("test@example.com");
        user2.setFirebaseUid("firebase-uid-123");
        user2.setRol("USER");
        user2.setActivo(1);

        // When & Then
        assertEquals(user1, user2);
        assertEquals(user1, user1);
    }

    @Test
    void testHashCode() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        user1.setCorreo("test@example.com");
        user1.setFirebaseUid("firebase-uid-123");

        User user2 = new User();
        user2.setId(1L);
        user2.setCorreo("test@example.com");
        user2.setFirebaseUid("firebase-uid-123");

        // When & Then
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setCorreo("test@example.com");
        user.setFirebaseUid("firebase-uid-123");
        user.setRol("USER");

        // When
        String toString = user.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("test@example.com"));
    }

    @Test
    void testDefaultConstructor() {
        // When
        User user = new User();

        // Then
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getCorreo());
        assertNull(user.getFirebaseUid());
        assertNull(user.getRol());
        assertNull(user.getCreatedAt());
    }
}
