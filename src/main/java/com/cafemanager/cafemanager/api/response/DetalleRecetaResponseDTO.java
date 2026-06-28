package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DetalleRecetaResponseDTO {

    private Long ingredienteId;

    private String ingrediente;

    private BigDecimal cantidad;

}