package com.grupo10.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo10.user_service.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByCorreo(String correo);

}
