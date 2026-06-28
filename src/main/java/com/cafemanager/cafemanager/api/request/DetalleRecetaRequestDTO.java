package com.cafemanager.cafemanager.api.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleRecetaRequestDTO {

    private Long ingredienteId;

    private BigDecimal cantidad;

}
