package com.cafemanager.cafemanager.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecetaRequestDTO {

    @NotNull
    private Long productoId;

    @NotEmpty
    @Valid
    private List<DetalleRecetaRequestDTO> detalles;

}