package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.MovimientoStockResponseDTO;
import com.cafemanager.cafemanager.domain.entity.MovimientoStock;

public class MovimientoStockMapper {


    public static MovimientoStockResponseDTO toResponse(
            MovimientoStock movimiento
    ){

        return new MovimientoStockResponseDTO(
                movimiento.getId(),
                movimiento.getIngrediente().getId(),
                movimiento.getIngrediente().getNombre(),
                movimiento.getTipoMovimiento(),
                movimiento.getCantidad(),
                movimiento.getFecha(),
                movimiento.getObservacion(),
                movimiento.getReferencia()
        );
    }
}