package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductoResponseDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private String categoria;

    private Boolean disponible;

    private Boolean activo;

}
