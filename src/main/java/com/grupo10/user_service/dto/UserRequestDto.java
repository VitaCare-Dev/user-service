package com.grupo10.user_service.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String correo;
    private String password;
    private String rol;

}
