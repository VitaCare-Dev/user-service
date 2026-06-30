package com.grupo10.user_service.controller;

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

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setCorreo("test@example.com");
        userRequestDto.setFirebaseUid("firebase-uid-123");
        userRequestDto.setRol("USER");

        userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setCorreo("test@example.com");
        userResponseDto.setRol("USER");
        userResponseDto.setActivo(1);
        userResponseDto.setCreatedAt(LocalDateTime.now());
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
    void getUserByFirebaseUid_ShouldReturnUser() throws Exception {
        // Given
        when(userService.getUserByFirebaseUid("firebase-uid-123")).thenReturn(userResponseDto);

        // When & Then
        mockMvc.perform(get("/api/users/firebase/firebase-uid-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value("USER"))
                .andExpect(jsonPath("$.activo").value(1));

        verify(userService, times(1)).getUserByFirebaseUid("firebase-uid-123");
    }
}
