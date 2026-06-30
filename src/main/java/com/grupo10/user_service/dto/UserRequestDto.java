package com.grupo10.user_service.dto;

import lombok.Data;

/**
 * DTO de solicitud para sincronizar un usuario creado en Firebase Authentication.
 *
 * <p>Transporta los datos necesarios para crear la fila correspondiente en
 * {@code tb_usuario}: correo electrónico, UID de Firebase y rol asignado.</p>
 */
@Data
public class UserRequestDto {

    /** Dirección de correo electrónico del usuario. */
    private String correo;

    /** Identificador único del usuario en Firebase Authentication. */
    private String firebaseUid;

    /** Rol del usuario dentro del sistema (p. ej. {@code ADMIN}, {@code USER}). */
    private String rol;

}
