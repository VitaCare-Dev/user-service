package com.grupo10.user_service.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.grupo10.user_service.repository.UserRepository;
import com.grupo10.user_service.model.User;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;

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
    
}
