package com.cafemanager.cafemanager.api.response;

import com.cafemanager.cafemanager.domain.enums.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class IngredienteResponseDTO {

    private Long id;
    private String nombre;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private BigDecimal costoCompra;
    private UnidadMedida unidadMedida;
    private Boolean activo;

}
