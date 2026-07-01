package com.cafemanager.cafemanager.api.request;

import com.cafemanager.cafemanager.domain.enums.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {

    private String nombre;

    private String email;

    private String password;

    private Rol rol;

}