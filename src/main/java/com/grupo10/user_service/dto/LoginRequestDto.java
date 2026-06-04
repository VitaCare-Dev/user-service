package com.grupo10.user_service.dto;

import lombok.Data;

@Data 
public class LoginRequestDto {
    private String correo;
    private String password;
}
