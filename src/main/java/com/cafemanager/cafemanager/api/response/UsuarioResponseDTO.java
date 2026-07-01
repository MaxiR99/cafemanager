package com.cafemanager.cafemanager.api.response;

import com.cafemanager.cafemanager.application.mapper.UsuarioMapper;
import com.cafemanager.cafemanager.domain.entity.Usuario;
import com.cafemanager.cafemanager.domain.enums.Rol;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private Rol rol;
    private Boolean activo;



}




