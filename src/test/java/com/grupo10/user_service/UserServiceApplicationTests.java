package com.grupo10.user_service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

class UserServiceApplicationTest {

	@Test
	void constructorInstanciaClase() {
		UserServiceApplication application = new UserServiceApplication();
		assertNotNull(application);
	}

	@Test
	void mainEjecutaSpringApplicationRunSinCargarContextoReal() {
		try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
			String[] args = new String[] {};

			UserServiceApplication.main(args);

			mockedSpringApplication.verify(() -> SpringApplication.run(UserServiceApplication.class, args));
		}
	}
}