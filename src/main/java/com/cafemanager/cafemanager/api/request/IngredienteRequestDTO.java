package com.cafemanager.cafemanager.api.request;

import com.cafemanager.cafemanager.domain.enums.UnidadMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class IngredienteRequestDTO {


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;


    @NotNull(message = "El stock actual es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private BigDecimal stockActual;


    @NotNull(message = "El stock minimo es obligatorio")
    @PositiveOrZero(message = "El stock minimo no puede ser negativo")
    private BigDecimal stockMinimo;


    @NotNull(message = "El costo de compra es obligatorio")
    @PositiveOrZero(message = "El costo no puede ser negativo")
    private BigDecimal costoCompra;


    @NotNull(message = "La unidad de medida es obligatoria")
    private UnidadMedida unidadMedida;


}