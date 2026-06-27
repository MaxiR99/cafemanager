package com.cafemanager.cafemanager.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompraRequestDTO {

    private Long proveedorId;

    private List<DetalleCompraRequestDTO> detalles;

}