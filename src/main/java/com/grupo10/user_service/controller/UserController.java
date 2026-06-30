package com.grupo10.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupo10.user_service.service.UserService;
import com.grupo10.user_service.dto.UserRequestDto;
import com.grupo10.user_service.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * Controlador REST para la gestión de usuarios.
 *
 * <p>Expone los endpoints bajo {@code /api/users} para sincronizar usuarios
 * autenticados en Firebase con {@code tb_usuario} y consultar su información.</p>
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
     * Sincroniza un usuario autenticado en Firebase con {@code tb_usuario}.
     *
     * @param request datos del usuario a sincronizar (correo, UID de Firebase y rol)
     * @return {@link UserResponseDto} con los datos del usuario creado y estado HTTP 201
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request) {
        UserResponseDto createdUser = userService.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
     * Obtiene los datos de un usuario por su UID de Firebase Authentication.
     *
     * @param firebaseUid UID de Firebase del usuario
     * @return {@link UserResponseDto} con los datos del usuario y estado HTTP 200
     */
    @GetMapping("/firebase/{firebaseUid}")
    public ResponseEntity<UserResponseDto> getUserByFirebaseUid(@PathVariable String firebaseUid) {
        UserResponseDto user = userService.getUserByFirebaseUid(firebaseUid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
