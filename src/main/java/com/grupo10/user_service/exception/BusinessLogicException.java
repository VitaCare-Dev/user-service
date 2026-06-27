package com.grupo10.user_service.exception;

/**
 * Excepción de propósito general para violaciones de reglas de negocio.
 *
 * <p>Disponible para escenarios donde ninguna otra excepción específica del dominio
 * aplica, pero la operación infringe una regla de negocio definida.</p>
 */
public class BusinessLogicException extends RuntimeException {

    /**
     * Crea la excepción con un mensaje que describe la regla de negocio violada.
     *
     * @param message descripción de la violación de regla de negocio
     */
    public BusinessLogicException(String message) {
        super(message);
    }

}
