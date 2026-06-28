package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecetaResponseDTO {

    private Long id;

    private Long productoId;

    private String producto;

    private Boolean activa;

    private List<DetalleRecetaResponseDTO> detalles;

}
