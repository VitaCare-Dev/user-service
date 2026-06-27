package com.grupo10.user_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para errores de la API.
 *
 * <p>Estandariza el formato de los mensajes de error devueltos por el
 * {@code GlobalExceptionHandler}, incluyendo descripción, código HTTP y
 * marca de tiempo del evento.</p>
 */
@Data
public class ErrorResponseDto {

    /** Descripción legible del error ocurrido. */
    private String message;

    /** Código de estado HTTP asociado al error (p. ej. 404, 401, 409). */
    private int status;

    /** Fecha y hora en que se produjo el error. */
    private LocalDateTime timestamp;

}
