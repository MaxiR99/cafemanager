package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.ProductoResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Producto;

public class ProductoMapper {

    public static ProductoResponseDTO toResponse(Producto producto){

        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCategoria().getNombre(),
                producto.getDisponible(),
                producto.getActivo()
        );

    }

}
