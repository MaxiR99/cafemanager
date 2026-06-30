package com.cafemanager.cafemanager.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleRecetaRequestDTO {

    @NotNull
    private Long ingredienteId;

    @NotNull
    @Positive
    private BigDecimal cantidad;

}
