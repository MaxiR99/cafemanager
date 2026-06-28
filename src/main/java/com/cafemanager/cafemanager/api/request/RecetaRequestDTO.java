package com.cafemanager.cafemanager.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecetaRequestDTO {

    private Long productoId;

    private List<DetalleRecetaRequestDTO> detalles;

}