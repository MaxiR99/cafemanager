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

    @NotNull(message = "Debe seleccionar un producto")
    private Long productoId;

    @NotEmpty(message = "La receta debe contener al menos un ingrediente")
    @Valid
    private List<DetalleRecetaRequestDTO> detalles;

}