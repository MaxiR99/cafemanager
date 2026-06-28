package com.cafemanager.cafemanager.api.response;

import com.cafemanager.cafemanager.domain.enums.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class VentaResponseDTO {

    private Long id;

    private LocalDateTime fecha;

    private BigDecimal total;

    private EstadoVenta estado;

    private List<DetalleVentaResponseDTO> detalles;

}
