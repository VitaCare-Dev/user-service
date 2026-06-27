package com.grupo10.user_service.dto;

import lombok.Data;

/**
 * DTO de solicitud para la autenticación de un usuario.
 *
 * <p>Contiene las credenciales necesarias para iniciar sesión:
 * correo electrónico y contraseña en texto plano.</p>
 */
@Data
public class LoginRequestDto {

    /** Dirección de correo electrónico del usuario. */
    private String correo;

    /** Contraseña en texto plano para verificar contra el hash almacenado. */
    private String password;

}
