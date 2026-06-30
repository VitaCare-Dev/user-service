package com.grupo10.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo10.user_service.model.User;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link User}.
 *
 * <p>Hereda las operaciones CRUD estándar de {@link JpaRepository} y añade
 * una consulta derivada para buscar usuarios por correo electrónico.</p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param correo dirección de correo a buscar
     * @return un {@link Optional} con el usuario si existe, o vacío si no se encuentra
     */
    Optional<User> findByCorreo(String correo);

    /**
     * Busca un usuario por su identificador de Firebase Authentication.
     *
     * @param firebaseUid UID de Firebase a buscar
     * @return un {@link Optional} con el usuario si existe, o vacío si no se encuentra
     */
    Optional<User> findByFirebaseUid(String firebaseUid);

}
