package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.UsuarioResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Usuario;

public class UsuarioMapper {


    public static UsuarioResponseDTO toResponse(
            Usuario usuario
    ) {

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getActivo()
        );
    }

}
