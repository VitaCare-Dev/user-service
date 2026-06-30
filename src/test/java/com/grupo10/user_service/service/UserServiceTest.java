package com.grupo10.user_service.service;

import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import com.grupo10.user_service.exception.DuplicateResourceException;
import com.grupo10.user_service.exception.ResourceNotFoundException;
import com.grupo10.user_service.model.User;
import com.grupo10.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setCorreo("test@example.com");
        user.setFirebaseUid("firebase-uid-123");
        user.setRol("USER");
        user.setActivo(1);
        user.setCreatedAt(LocalDateTime.now());

        userRequestDto = new UserRequestDto();
        userRequestDto.setCorreo("test@example.com");
        userRequestDto.setFirebaseUid("firebase-uid-123");
        userRequestDto.setRol("USER");
    }

    @Test
    void createUser_ShouldReturnUserResponseDto() {
        // Given
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByFirebaseUid("firebase-uid-123")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        UserResponseDto result = userService.createUser(userRequestDto);

        // Then
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getCorreo(), result.getCorreo());
        assertEquals(user.getRol(), result.getRol());
        assertEquals(user.getActivo(), result.getActivo());
        assertNotNull(result.getCreatedAt());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_WhenCorreoAlreadyExists_ShouldThrowDuplicateResourceException() {
        // Given
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.of(user));

        // When & Then
        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> userService.createUser(userRequestDto));

        assertEquals("Ya existe un usuario con ese correo", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_WhenFirebaseUidAlreadyExists_ShouldThrowDuplicateResourceException() {
        // Given
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByFirebaseUid("firebase-uid-123")).thenReturn(Optional.of(user));

        // When & Then
        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> userService.createUser(userRequestDto));

        assertEquals("Ya existe un usuario con ese UID de Firebase", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUserResponseDto() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        UserResponseDto result = userService.getUserById(1L);

        // Then
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getCorreo(), result.getCorreo());
        assertEquals(user.getRol(), result.getRol());
        assertEquals(user.getActivo(), result.getActivo());
        assertEquals(user.getCreatedAt(), result.getCreatedAt());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L));

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserByFirebaseUid_WhenUserExists_ShouldReturnUserResponseDto() {
        // Given
        when(userRepository.findByFirebaseUid("firebase-uid-123")).thenReturn(Optional.of(user));

        // When
        UserResponseDto result = userService.getUserByFirebaseUid("firebase-uid-123");

        // Then
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getCorreo(), result.getCorreo());
        assertEquals(user.getRol(), result.getRol());
        assertEquals(user.getActivo(), result.getActivo());

        verify(userRepository, times(1)).findByFirebaseUid("firebase-uid-123");
    }

    @Test
    void getUserByFirebaseUid_WhenUserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        when(userRepository.findByFirebaseUid("firebase-uid-123")).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserByFirebaseUid("firebase-uid-123"));

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userRepository, times(1)).findByFirebaseUid("firebase-uid-123");
    }
}
