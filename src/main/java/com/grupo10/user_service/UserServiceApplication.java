package com.grupo10.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada principal del microservicio de usuarios.
 *
 * <p>Inicializa el contexto de Spring Boot y arranca todos los componentes
 * del servicio, incluyendo la configuración de seguridad, persistencia y
 * los endpoints REST.</p>
 */
@SpringBootApplication
public class UserServiceApplication {

	/**
	 * Arranca la aplicación Spring Boot.
	 *
	 * @param args argumentos de línea de comandos pasados al arranque
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
