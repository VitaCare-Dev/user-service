package com.grupo10.user_service.exception;

/**
 * Excepción lanzada ante fallos de autenticación o validación de credenciales.
 *
 * <p>Cubre escenarios como contraseña incorrecta, cuenta inactiva o intento de
 * establecer una contraseña idéntica a la actual. El {@code GlobalExceptionHandler}
 * la mapea a una respuesta HTTP 401.</p>
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Crea la excepción con un mensaje que describe la causa del fallo de credenciales.
     *
     * @param message descripción del motivo por el que las credenciales son inválidas
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }

}
