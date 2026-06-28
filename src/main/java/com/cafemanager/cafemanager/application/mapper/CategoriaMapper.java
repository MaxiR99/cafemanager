package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.CategoriaResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Categoria;

public class CategoriaMapper {

    public static CategoriaResponseDTO toResponse(Categoria categoria){

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getActivo()
        );

    }

}
