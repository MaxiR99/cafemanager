package com.cafemanager.cafemanager.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompraRequestDTO {

    @NotNull(message = "Debe seleccionar un proveedor")
    private Long proveedorId;

    @NotEmpty(message = "La compra debe tener al menos un detalle")
    @Valid
    private List<DetalleCompraRequestDTO> detalles;

}