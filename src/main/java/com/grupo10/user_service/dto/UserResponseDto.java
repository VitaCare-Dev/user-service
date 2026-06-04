package com.grupo10.user_service.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponseDto {

    private int id;
    private String correo;
    private String rol;
    private boolean estado;
    private LocalDateTime createdAt;

}