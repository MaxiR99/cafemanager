package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.request.IngredienteRequestDTO;
import com.cafemanager.cafemanager.api.response.IngredienteResponseDTO;
import com.cafemanager.cafemanager.api.response.IngredienteStockBajoResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;

public class IngredienteMapper {

    private IngredienteMapper() {
    }

    public static Ingrediente toEntity(IngredienteRequestDTO dto) {

        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setNombre(dto.getNombre());
        ingrediente.setStockActual(dto.getStockActual());
        ingrediente.setStockMinimo(dto.getStockMinimo());
        ingrediente.setCostoCompra(dto.getCostoCompra());
        ingrediente.setUnidadMedida(dto.getUnidadMedida());


        return ingrediente;
    }

    public static IngredienteResponseDTO toResponse(Ingrediente ingrediente) {

        return new IngredienteResponseDTO(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getStockActual(),
                ingrediente.getStockMinimo(),
                ingrediente.getCostoCompra(),
                ingrediente.getUnidadMedida(),
                ingrediente.getActivo()
        );
    }

    public static IngredienteStockBajoResponseDTO toStockBajoResponse(Ingrediente ingrediente) {

        return new IngredienteStockBajoResponseDTO(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getStockActual(),
                ingrediente.getStockMinimo(),
                ingrediente.getStockMinimo().subtract(ingrediente.getStockActual()),
                ingrediente.getUnidadMedida()
        );

    }

}