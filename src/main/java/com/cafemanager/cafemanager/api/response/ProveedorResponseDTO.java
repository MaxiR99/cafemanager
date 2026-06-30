package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProveedorResponseDTO {

    private Long id;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private Boolean activo;

}