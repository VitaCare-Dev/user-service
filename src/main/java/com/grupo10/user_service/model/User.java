package com.grupo10.user_service.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad JPA que representa a un usuario en la tabla {@code tb_usuario}.
 *
 * <p>Almacena la referencia al usuario de Firebase Authentication, el rol y
 * el estado de activación. La autenticación (correo y contraseña) la maneja
 * Firebase; esta tabla solo sincroniza el {@code firebase_uid} con los datos
 * propios del dominio.</p>
 */
@Data
@Entity
@Table(name = "tb_usuario")
public class User {

    /** Identificador único del usuario, generado automáticamente por la base de datos. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    /** Dirección de correo electrónico del usuario; actúa como nombre de usuario. */
    @Column(name = "email")
    private String correo;

    /** Identificador único del usuario en Firebase Authentication. */
    @Column(name = "firebase_uid")
    private String firebaseUid;

    /** Rol asignado al usuario dentro del sistema (p. ej. {@code ADMIN}, {@code USER}). */
    @Column(name = "rol")
    private String rol;

    /** Indica si la cuenta está activa ({@code 1}) o inactiva ({@code 0}). */
    @Column(name = "activo")
    private int activo;

    /** Fecha y hora en que se creó la cuenta del usuario. */
    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

}
