package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PerdidaResponseDTO {


    private Long id;

    private Long ingredienteId;

    private String ingrediente;

    private BigDecimal cantidad;

    private String motivo;

    private LocalDateTime fecha;

    private String observacion;

    private String usuario;

}
