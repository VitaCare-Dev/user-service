package com.grupo10.user_service.exception;

/**
 * Excepción lanzada cuando se intenta crear un recurso que ya existe.
 *
 * <p>Pensada para casos como el registro de un correo electrónico duplicado.
 * El {@code GlobalExceptionHandler} la mapea a una respuesta HTTP 409.</p>
 */
public class DuplicateResourceException extends RuntimeException {

    /**
     * Crea la excepción con un mensaje que identifica el recurso duplicado.
     *
     * @param message descripción del recurso que ya existe en el sistema
     */
    public DuplicateResourceException(String message) {
        super(message);
    }

}
