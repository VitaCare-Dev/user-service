package com.grupo10.user_service.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Test
    void corsConfigurerCreaConfiguracionCorsYAplicaMapeos() {
        SecurityConfig securityConfig = new SecurityConfig();

        CorsRegistry registry = mock(CorsRegistry.class);
        CorsRegistration registration = mock(CorsRegistration.class);

        when(registry.addMapping("/api/**")).thenReturn(registration);
        when(registration.allowedOrigins("*")).thenReturn(registration);
        when(registration.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")).thenReturn(registration);
        when(registration.allowedHeaders("*")).thenReturn(registration);
        when(registration.allowCredentials(false)).thenReturn(registration);
        when(registration.maxAge(3600)).thenReturn(registration);

        WebMvcConfigurer configurer = securityConfig.corsConfigurer();

        assertNotNull(configurer);

        configurer.addCorsMappings(registry);

        verify(registry).addMapping("/api/**");
        verify(registration).allowedOrigins("*");
        verify(registration).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        verify(registration).allowedHeaders("*");
        verify(registration).allowCredentials(false);
        verify(registration).maxAge(3600);
    }
}