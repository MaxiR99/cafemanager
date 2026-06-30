package com.cafemanager.cafemanager.api.request;

import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockRequestDTO {

    @NotNull
    private Long ingredienteId;

    @NotNull
    @Positive
    private BigDecimal cantidad;

    @NotNull
    private TipoMovimientoStock tipoMovimiento;

    @NotBlank
    private String observacion;

}