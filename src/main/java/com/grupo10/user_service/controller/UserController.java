package com.grupo10.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupo10.user_service.service.UserService;
import com.grupo10.user_service.dto.ChangePasswordRequestDto;
import com.grupo10.user_service.dto.LoginRequestDto;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



/**
 * Controlador REST para la gestión de usuarios.
 *
 * <p>Expone los endpoints bajo {@code /api/users} para registrar usuarios,
 * autenticarlos, consultar su información y actualizar su contraseña.</p>
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Construye el controlador inyectando el servicio de usuarios.
     *
     * @param userService servicio con la lógica de negocio de usuarios
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request datos del usuario a registrar (correo, contraseña y rol)
     * @return {@link UserResponseDto} con los datos del usuario creado y estado HTTP 201
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request) {
        UserResponseDto createdUser = userService.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Autentica un usuario mediante correo y contraseña.
     *
     * @param request credenciales del usuario (correo y contraseña)
     * @return {@link UserResponseDto} con los datos del usuario autenticado y estado HTTP 200
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto request) {

        UserResponseDto response = userService.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Obtiene los datos de un usuario por su identificador.
     *
     * @param id identificador único del usuario
     * @return {@link UserResponseDto} con los datos del usuario y estado HTTP 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param id      identificador único del usuario
     * @param request objeto con la contraseña actual y la nueva contraseña
     * @return mensaje de confirmación y estado HTTP 200
     */
    @PutMapping("/{id}/password")
    public ResponseEntity<String> cambiarPassword(@PathVariable Long id, @RequestBody ChangePasswordRequestDto request) {
        userService.cambiarPassword(id, request);
        return new ResponseEntity<>("Contraseña actualizada exitosamente", HttpStatus.OK);
    }
    

    

}
