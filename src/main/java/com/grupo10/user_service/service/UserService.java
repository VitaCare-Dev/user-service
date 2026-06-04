package com.grupo10.user_service.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.grupo10.user_service.repository.UserRepository;
import com.grupo10.user_service.model.User;
import com.grupo10.user_service.dto.LoginRequestDto;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import com.grupo10.user_service.exception.InvalidCredentialsException;
import com.grupo10.user_service.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(UserRequestDto request) {
        User usuario = new User();
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol());
        usuario.setActivo(1); 
        usuario.setCreatedAt(LocalDateTime.now()); 
        User usuarioGuardado = userRepository.save(usuario);
        UserResponseDto response = new UserResponseDto();
        response.setId(usuarioGuardado.getId());
        response.setCorreo(usuarioGuardado.getCorreo());
        response.setRol(usuarioGuardado.getRol());
        response.setActivo(usuarioGuardado.getActivo());
        response.setCreatedAt(usuarioGuardado.getCreatedAt());
        return response;
    }

    public UserResponseDto getUserById(Long id) {
        User usuario = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        UserResponseDto response = new UserResponseDto();
        response.setId(usuario.getId());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol());
        response.setActivo(usuario.getActivo());
        response.setCreatedAt(usuario.getCreatedAt());
        return response;
    }

    public UserResponseDto login(LoginRequestDto request) {
        User usuario = userRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new ResourceNotFoundException("Credenciales inválidas"));
        if (usuario.getActivo() == 0) {
            throw new InvalidCredentialsException("La cuenta de usuario está inactiva");
        }
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }
        UserResponseDto response = new UserResponseDto();
        response.setId(usuario.getId());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol());
        response.setActivo(usuario.getActivo());
        response.setCreatedAt(usuario.getCreatedAt());
        return response;
    }
    
}
