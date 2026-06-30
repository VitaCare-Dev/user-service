package com.grupo10.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuración de CORS del microservicio.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configura la política CORS para todos los endpoints bajo {@code /api/**}.
     *
     * <p>Permite cualquier origen, los métodos GET, POST, PUT, DELETE y OPTIONS,
     * cualquier cabecera, y establece un tiempo de caché de preflight de 3600 segundos.
     * Las credenciales de sesión quedan deshabilitadas.</p>
     *
     * @return configurador CORS que aplica la política definida
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

}