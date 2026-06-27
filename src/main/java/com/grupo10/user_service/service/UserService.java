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
import com.grupo10.user_service.dto.ChangePasswordRequestDto;

import java.time.LocalDateTime;

/**
 * Servicio con la lógica de negocio para la gestión de usuarios.
 *
 * <p>Coordina las operaciones de creación, consulta, autenticación y cambio
 * de contraseña, haciendo uso de {@link UserRepository} para la persistencia
 * y {@link PasswordEncoder} para el cifrado BCrypt de contraseñas.</p>
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Construye el servicio inyectando el repositorio y el codificador de contraseñas.
     *
     * @param userRepository  repositorio JPA para operaciones de base de datos
     * @param passwordEncoder codificador BCrypt para hash y verificación de contraseñas
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Crea y persiste un nuevo usuario en el sistema.
     *
     * <p>La contraseña se almacena cifrada con BCrypt. El usuario se crea con
     * estado activo ({@code activo = 1}) y marca de tiempo de creación actual.</p>
     *
     * @param request datos del usuario a registrar
     * @return {@link UserResponseDto} con los datos persistidos del nuevo usuario
     */
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

    /**
     * Recupera los datos de un usuario por su identificador único.
     *
     * @param id identificador del usuario a buscar
     * @return {@link UserResponseDto} con los datos del usuario encontrado
     * @throws ResourceNotFoundException si no existe un usuario con el {@code id} indicado
     */
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

    /**
     * Autentica a un usuario verificando su correo, estado de cuenta y contraseña.
     *
     * @param request credenciales de acceso (correo y contraseña en texto plano)
     * @return {@link UserResponseDto} con los datos del usuario autenticado
     * @throws ResourceNotFoundException     si el correo no corresponde a ningún usuario
     * @throws InvalidCredentialsException   si la cuenta está inactiva o la contraseña es incorrecta
     */
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

    /**
     * Actualiza la contraseña de un usuario tras verificar la contraseña actual.
     *
     * <p>Valida que la contraseña actual coincida con el hash almacenado y que
     * la nueva contraseña sea diferente a la vigente antes de persistir el cambio.</p>
     *
     * @param id      identificador del usuario cuya contraseña se actualizará
     * @param request objeto con la contraseña actual y la nueva contraseña
     * @throws ResourceNotFoundException   si no existe un usuario con el {@code id} indicado
     * @throws InvalidCredentialsException si la contraseña actual es incorrecta o la nueva
     *                                     contraseña es igual a la actual
     */
    public void cambiarPassword(Long id, ChangePasswordRequestDto request) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getCurrentPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("Contraseña actual incorrecta");
        }
        if (passwordEncoder.matches(request.getNewPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("La nueva contraseña no puede ser igual a la actual");
        }

        usuario.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(usuario);
    }
    
}
