package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.DetalleRecetaResponseDTO;
import com.cafemanager.cafemanager.api.response.RecetaResponseDTO;
import com.cafemanager.cafemanager.domain.entity.DetalleReceta;
import com.cafemanager.cafemanager.domain.entity.Receta;

import java.util.List;

public class RecetaMapper {

    public static RecetaResponseDTO toResponse(Receta receta) {

        List<DetalleRecetaResponseDTO> detalles = receta.getDetalles()
                .stream()
                .map(RecetaMapper::toDetalleResponse)
                .toList();

        return new RecetaResponseDTO(
                receta.getId(),
                receta.getProducto().getId(),
                receta.getProducto().getNombre(),
                receta.getActiva(),
                detalles
        );
    }

    private static DetalleRecetaResponseDTO toDetalleResponse(
            DetalleReceta detalle) {

        return new DetalleRecetaResponseDTO(
                detalle.getIngrediente().getId(),
                detalle.getIngrediente().getNombre(),
                detalle.getCantidad()
        );
    }

}