package com.grupo10.user_service.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String correo;
    private String rol;
    private int activo;
    private LocalDateTime createdAt;

}