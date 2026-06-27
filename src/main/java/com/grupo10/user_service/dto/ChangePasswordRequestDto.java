package com.grupo10.user_service.dto;

import lombok.Data;

/**
 * DTO de solicitud para el cambio de contraseña de un usuario.
 *
 * <p>Requiere la contraseña actual para verificar la identidad del solicitante
 * antes de aplicar la nueva contraseña.</p>
 */
@Data
public class ChangePasswordRequestDto {

    /** Contraseña actual del usuario en texto plano. */
    private String currentPassword;

    /** Nueva contraseña deseada en texto plano; no debe ser igual a la actual. */
    private String newPassword;

}
