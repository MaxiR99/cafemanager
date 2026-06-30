package com.cafemanager.cafemanager.api.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DetalleCompraRequestDTO {

    @NotNull
    private Long ingredienteId;

    @NotNull
    @Positive
    private BigDecimal cantidad;

    @NotNull
    @Positive
    private BigDecimal precioUnitario;

    @Future
    private LocalDate fechaVencimiento;

}