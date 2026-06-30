package com.grupo10.user_service.service;

import org.springframework.stereotype.Service;
import com.grupo10.user_service.repository.UserRepository;
import com.grupo10.user_service.model.User;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import com.grupo10.user_service.exception.DuplicateResourceException;
import com.grupo10.user_service.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

/**
 * Servicio con la lógica de negocio para la gestión de usuarios.
 *
 * <p>Coordina la sincronización de usuarios autenticados vía Firebase
 * Authentication con {@code tb_usuario}, haciendo uso de {@link UserRepository}
 * para la persistencia. La autenticación en sí (login, contraseñas) la maneja
 * Firebase, no este servicio.</p>
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Construye el servicio inyectando el repositorio.
     *
     * @param userRepository repositorio JPA para operaciones de base de datos
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Sincroniza un usuario autenticado en Firebase con {@code tb_usuario}.
     *
     * <p>El usuario se crea con estado activo ({@code activo = 1}) y marca de
     * tiempo de creación actual.</p>
     *
     * @param request datos del usuario a sincronizar (correo, UID de Firebase y rol)
     * @return {@link UserResponseDto} con los datos persistidos del nuevo usuario
     * @throws DuplicateResourceException si ya existe un usuario con el mismo correo o UID de Firebase
     */
    public UserResponseDto createUser(UserRequestDto request) {
        if (userRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un usuario con ese correo");
        }
        if (userRepository.findByFirebaseUid(request.getFirebaseUid()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un usuario con ese UID de Firebase");
        }
        User usuario = new User();
        usuario.setCorreo(request.getCorreo());
        usuario.setFirebaseUid(request.getFirebaseUid());
        usuario.setRol(request.getRol());
        usuario.setActivo(1);
        usuario.setCreatedAt(LocalDateTime.now());
        User usuarioGuardado = userRepository.save(usuario);
        return toResponse(usuarioGuardado);
    }

    /**
     * Recupera los datos de un usuario por su identificador único.
     *
     * @param id identificador del usuario a buscar
     * @return {@link UserResponseDto} con los datos del usuario encontrado
     * @throws ResourceNotFoundException si no existe un usuario con el {@code id} indicado
     */
    public UserResponseDto getUserById(Long id) {
        User usuario = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return toResponse(usuario);
    }

    /**
     * Recupera los datos de un usuario por su UID de Firebase Authentication.
     *
     * <p>Pensado para que el BFF u otros servicios resuelvan el {@code id_usuario}
     * interno, el rol y el estado de la cuenta a partir del UID presente en el
     * ID Token de Firebase.</p>
     *
     * @param firebaseUid UID de Firebase a buscar
     * @return {@link UserResponseDto} con los datos del usuario encontrado
     * @throws ResourceNotFoundException si no existe un usuario con ese UID
     */
    public UserResponseDto getUserByFirebaseUid(String firebaseUid) {
        User usuario = userRepository.findByFirebaseUid(firebaseUid)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return toResponse(usuario);
    }

    private UserResponseDto toResponse(User usuario) {
        UserResponseDto response = new UserResponseDto();
        response.setId(usuario.getId());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol());
        response.setActivo(usuario.getActivo());
        response.setCreatedAt(usuario.getCreatedAt());
        return response;
    }

}
