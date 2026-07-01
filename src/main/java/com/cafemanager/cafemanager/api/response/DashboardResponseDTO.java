package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class DashboardResponseDTO {


    private Long ventasHoy;

    private BigDecimal ingresosHoy;

    private Long perdidasHoy;

    private BigDecimal costoPerdidasHoy;

    private Long productosVendidosHoy;

    private String productoMasVendido;

    private Long cantidadProductoMasVendido;

    private List<IngredienteStockBajoResponseDTO> ingredientesStockBajo;

}