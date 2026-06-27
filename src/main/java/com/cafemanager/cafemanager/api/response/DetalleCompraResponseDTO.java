package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DetalleCompraResponseDTO {

    private String ingrediente;

    private BigDecimal cantidad;

    private BigDecimal precioUnitario;

    private LocalDate fechaVencimiento;

}