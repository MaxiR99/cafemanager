package com.cafemanager.cafemanager.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaRequestDTO {

    @NotNull
    private Long productoId;

    @NotNull
    @Min(value = 1)
    private Integer cantidad;

}