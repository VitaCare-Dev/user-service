package com.grupo10.user_service.service;

import com.grupo10.user_service.dto.ChangePasswordRequestDto;
import com.grupo10.user_service.dto.LoginRequestDto;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import com.grupo10.user_service.exception.InvalidCredentialsException;
import com.grupo10.user_service.exception.ResourceNotFoundException;
import com.grupo10.user_service.model.User;
import com.grupo10.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDto userRequestDto;
    private LoginRequestDto loginRequestDto;
    private ChangePasswordRequestDto changePasswordRequestDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setCorreo("test@example.com");
        user.setPassword("encodedPassword");
        user.setRol("USER");
        user.setActivo(1);
        user.setCreatedAt(LocalDateTime.now());

        userRequestDto = new UserRequestDto();
        userRequestDto.setCorreo("test@example.com");
        userRequestDto.setPassword("password123");
        userRequestDto.setRol("USER");

        loginRequestDto = new LoginRequestDto();
        loginRequestDto.setCorreo("test@example.com");
        loginRequestDto.setPassword("password123");

        changePasswordRequestDto = new ChangePasswordRequestDto();
        changePasswordRequestDto.setCurrentPassword("password123");
        changePasswordRequestDto.setNewPassword("newPassword123");
    }

    @Test
    void createUser_ShouldReturnUserResponseDto() {
        // Given
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
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

        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(User.class));
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
    void login_WhenCredentialsAreValid_ShouldReturnUserResponseDto() {
        // Given
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        // When
        UserResponseDto result = userService.login(loginRequestDto);

        // Then
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getCorreo(), result.getCorreo());
        assertEquals(user.getRol(), result.getRol());
        assertEquals(user.getActivo(), result.getActivo());
        assertEquals(user.getCreatedAt(), result.getCreatedAt());

        verify(userRepository, times(1)).findByCorreo("test@example.com");
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword");
    }

    @Test
    void login_WhenUserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.login(loginRequestDto));

        assertEquals("Credenciales inválidas", exception.getMessage());
        verify(userRepository, times(1)).findByCorreo("test@example.com");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void login_WhenUserIsInactive_ShouldThrowInvalidCredentialsException() {
        // Given
        user.setActivo(0);
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.of(user));

        // When & Then
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> userService.login(loginRequestDto));

        assertEquals("La cuenta de usuario está inactiva", exception.getMessage());
        verify(userRepository, times(1)).findByCorreo("test@example.com");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void login_WhenPasswordIsIncorrect_ShouldThrowInvalidCredentialsException() {
        // Given
        when(userRepository.findByCorreo("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(false);

        // When & Then
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> userService.login(loginRequestDto));

        assertEquals("Credenciales inválidas", exception.getMessage());
        verify(userRepository, times(1)).findByCorreo("test@example.com");
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword");
    }

    @Test
    void cambiarPassword_WhenValidRequest_ShouldUpdatePassword() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
        when(passwordEncoder.matches("newPassword123", "encodedPassword")).thenReturn(false);
        when(passwordEncoder.encode("newPassword123")).thenReturn("newEncodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        userService.cambiarPassword(1L, changePasswordRequestDto);

        // Then
        verify(userRepository, times(1)).findById(1L);
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword");
        verify(passwordEncoder, times(1)).matches("newPassword123", "encodedPassword");
        verify(passwordEncoder, times(1)).encode("newPassword123");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void cambiarPassword_WhenUserDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.cambiarPassword(1L, changePasswordRequestDto));

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void cambiarPassword_WhenCurrentPasswordIsIncorrect_ShouldThrowInvalidCredentialsException() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(false);

        // When & Then
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> userService.cambiarPassword(1L, changePasswordRequestDto));

        assertEquals("Contraseña actual incorrecta", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void cambiarPassword_WhenNewPasswordIsSameAsOld_ShouldThrowInvalidCredentialsException() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
        when(passwordEncoder.matches("newPassword123", "encodedPassword")).thenReturn(true);

        // When & Then
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> userService.cambiarPassword(1L, changePasswordRequestDto));

        assertEquals("La nueva contraseña no puede ser igual a la actual", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword");
        verify(passwordEncoder, times(1)).matches("newPassword123", "encodedPassword");
        verify(userRepository, never()).save(any(User.class));
    }
}
