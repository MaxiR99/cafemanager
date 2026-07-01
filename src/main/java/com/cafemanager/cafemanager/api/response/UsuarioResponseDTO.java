package com.cafemanager.cafemanager.api.response;

import com.cafemanager.cafemanager.domain.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private Rol rol;
    private Boolean activo;

}