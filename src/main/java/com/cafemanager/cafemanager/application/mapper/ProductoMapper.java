package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.request.ProductoRequestDTO;
import com.cafemanager.cafemanager.api.response.ProductoResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Categoria;
import com.cafemanager.cafemanager.domain.entity.Producto;

public class ProductoMapper {

    public static Producto toEntity(
            ProductoRequestDTO dto,
            Categoria categoria) {

        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(categoria);


        return producto;
    }

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
