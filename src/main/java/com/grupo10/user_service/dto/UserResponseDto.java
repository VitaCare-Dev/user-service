package com.grupo10.user_service.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * DTO de respuesta con los datos públicos de un usuario.
 *
 * <p>Se omite deliberadamente la contraseña para evitar exponer
 * información sensible en las respuestas de la API.</p>
 */
@Data
public class UserResponseDto {

    /** Identificador único del usuario. */
    private Long id;

    /** Dirección de correo electrónico del usuario. */
    private String correo;

    /** Rol del usuario dentro del sistema. */
    private String rol;

    /** Estado de la cuenta: {@code 1} activa, {@code 0} inactiva. */
    private int activo;

    /** Fecha y hora de creación de la cuenta. */
    private LocalDateTime createdAt;

}
