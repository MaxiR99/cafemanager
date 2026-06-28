package com.cafemanager.cafemanager.api.response;

import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class MovimientoStockResponseDTO {

    private Long id;

    private Long ingredienteId;

    private String ingrediente;

    private TipoMovimientoStock tipoMovimiento;

    private BigDecimal cantidad;

    private LocalDateTime fecha;

    private String observacion;

    private String referencia;
}