package com.cafemanager.cafemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CompraResumenResponseDTO {

    private Long id;

    private String proveedor;

    private LocalDateTime fecha;

    private BigDecimal total;

}
