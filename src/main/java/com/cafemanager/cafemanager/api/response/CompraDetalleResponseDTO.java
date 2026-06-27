package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompraDetalleResponseDTO {

    private Long id;

    private String proveedor;

    private LocalDateTime fecha;

    private BigDecimal total;

    private List<DetalleCompraResponseDTO> detalles;

}