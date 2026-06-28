package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriaResponseDTO {

    private Long id;
    private String nombre;
    private Boolean activo;

}
