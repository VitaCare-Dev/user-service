package com.grupo10.user_service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordRequestDtoTest {

    @Test
    void testGettersAndSetters() {
        // Given
        ChangePasswordRequestDto dto = new ChangePasswordRequestDto();
        String currentPassword = "oldPassword123";
        String newPassword = "newPassword456";

        // When
        dto.setCurrentPassword(currentPassword);
        dto.setNewPassword(newPassword);

        // Then
        assertEquals(currentPassword, dto.getCurrentPassword());
        assertEquals(newPassword, dto.getNewPassword());
    }

    @Test
    void testEquals() {
        // Given
        ChangePasswordRequestDto dto1 = new ChangePasswordRequestDto();
        dto1.setCurrentPassword("oldPassword123");
        dto1.setNewPassword("newPassword456");

        ChangePasswordRequestDto dto2 = new ChangePasswordRequestDto();
        dto2.setCurrentPassword("oldPassword123");
        dto2.setNewPassword("newPassword456");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto1);
    }

    @Test
    void testHashCode() {
        // Given
        ChangePasswordRequestDto dto1 = new ChangePasswordRequestDto();
        dto1.setCurrentPassword("oldPassword123");
        dto1.setNewPassword("newPassword456");

        ChangePasswordRequestDto dto2 = new ChangePasswordRequestDto();
        dto2.setCurrentPassword("oldPassword123");
        dto2.setNewPassword("newPassword456");

        // When & Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        ChangePasswordRequestDto dto = new ChangePasswordRequestDto();
        dto.setCurrentPassword("oldPassword123");
        dto.setNewPassword("newPassword456");

        // When
        String toString = dto.toString();

        // Then
        assertNotNull(toString);
    }
}
