package com.cafemanager.cafemanager.api.request;

import com.cafemanager.cafemanager.domain.enums.MotivoPerdida;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PerdidaRequestDTO {


    @NotNull(message = "El ingrediente es obligatorio")
    private Long ingredienteId;


    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.001", message = "La cantidad debe ser mayor a cero")
    private BigDecimal cantidad;


    @NotNull(message = "El motivo es obligatorio")
    private MotivoPerdida motivo;


    private String observacion;

}
