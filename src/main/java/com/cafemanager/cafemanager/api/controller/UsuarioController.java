package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.response.UsuarioResponseDTO;
import com.cafemanager.cafemanager.application.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;


    public UsuarioController(
            UsuarioService usuarioService
    ) {
        this.usuarioService = usuarioService;
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UsuarioResponseDTO> listar() {

        return usuarioService.listar();
    }

}