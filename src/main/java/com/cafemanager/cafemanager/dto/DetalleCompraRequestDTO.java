package com.cafemanager.cafemanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DetalleCompraRequestDTO {

    private Long ingredienteId;

    private BigDecimal cantidad;

    private BigDecimal precioUnitario;

    private LocalDate fechaVencimiento;

}