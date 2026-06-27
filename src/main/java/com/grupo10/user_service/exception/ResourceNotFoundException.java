package com.grupo10.user_service.exception;

/**
 * Excepción lanzada cuando no se encuentra un recurso solicitado.
 *
 * <p>Se usa, por ejemplo, cuando un usuario no existe en la base de datos.
 * El {@code GlobalExceptionHandler} la mapea a una respuesta HTTP 404.</p>
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Crea la excepción con un mensaje descriptivo del recurso no encontrado.
     *
     * @param message descripción del recurso que no pudo ser localizado
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
