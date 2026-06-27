package com.grupo10.user_service.dto;

import lombok.Data;

/**
 * DTO de solicitud para el registro de un nuevo usuario.
 *
 * <p>Transporta los datos necesarios para crear una cuenta: correo electrónico,
 * contraseña en texto plano (se cifrará en la capa de servicio) y rol asignado.</p>
 */
@Data
public class UserRequestDto {

    /** Dirección de correo electrónico del usuario. */
    private String correo;

    /** Contraseña en texto plano; se almacenará cifrada con BCrypt. */
    private String password;

    /** Rol del usuario dentro del sistema (p. ej. {@code ADMIN}, {@code USER}). */
    private String rol;

}
