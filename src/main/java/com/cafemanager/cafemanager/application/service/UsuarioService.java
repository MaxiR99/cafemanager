package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.response.UsuarioResponseDTO;
import com.cafemanager.cafemanager.application.mapper.UsuarioMapper;
import com.cafemanager.cafemanager.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;


    public UsuarioService(
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
    }




    public List<UsuarioResponseDTO> listar() {

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .toList();

    }

}