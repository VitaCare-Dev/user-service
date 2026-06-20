package com.grupo10.user_service.controller;

import com.grupo10.user_service.dto.ChangePasswordRequestDto;
import com.grupo10.user_service.dto.LoginRequestDto;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import com.grupo10.user_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;
    private LoginRequestDto loginRequestDto;
    private ChangePasswordRequestDto changePasswordRequestDto;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setCorreo("test@example.com");
        userRequestDto.setPassword("password123");
        userRequestDto.setRol("USER");

        userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setCorreo("test@example.com");
        userResponseDto.setRol("USER");
        userResponseDto.setActivo(1);
        userResponseDto.setCreatedAt(LocalDateTime.now());

        loginRequestDto = new LoginRequestDto();
        loginRequestDto.setCorreo("test@example.com");
        loginRequestDto.setPassword("password123");

        changePasswordRequestDto = new ChangePasswordRequestDto();
        changePasswordRequestDto.setCurrentPassword("password123");
        changePasswordRequestDto.setNewPassword("newPassword123");
    }

    @Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        // Given
        when(userService.createUser(any(UserRequestDto.class))).thenReturn(userResponseDto);

        // When & Then
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value("USER"))
                .andExpect(jsonPath("$.activo").value(1));

        verify(userService, times(1)).createUser(any(UserRequestDto.class));
    }

    @Test
    void login_ShouldReturnUserResponseDto() throws Exception {
        // Given
        when(userService.login(any(LoginRequestDto.class))).thenReturn(userResponseDto);

        // When & Then
        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value("USER"))
                .andExpect(jsonPath("$.activo").value(1));

        verify(userService, times(1)).login(any(LoginRequestDto.class));
    }

    @Test
    void getUserById_ShouldReturnUser() throws Exception {
        // Given
        when(userService.getUserById(1L)).thenReturn(userResponseDto);

        // When & Then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value("USER"))
                .andExpect(jsonPath("$.activo").value(1));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void cambiarPassword_ShouldReturnSuccessMessage() throws Exception {
        // Given
        doNothing().when(userService).cambiarPassword(eq(1L), any(ChangePasswordRequestDto.class));

        // When & Then
        mockMvc.perform(put("/api/users/1/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(changePasswordRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Contraseña actualizada exitosamente"));

        verify(userService, times(1)).cambiarPassword(eq(1L), any(ChangePasswordRequestDto.class));
    }
}
