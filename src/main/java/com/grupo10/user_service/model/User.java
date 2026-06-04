package com.grupo10.user_service.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Data
@Entity
@Table(name = "tb_usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    
    @Column(name = "email")
    private String correo;
   
    @Column(name = "password_hash")
    private String password;
    
    @Column(name = "rol")
    private String rol;

    @Column(name = "activo")
    private int activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;



}
