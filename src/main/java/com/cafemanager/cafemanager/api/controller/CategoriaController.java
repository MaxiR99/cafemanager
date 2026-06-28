package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.CategoriaRequestDTO;
import com.cafemanager.cafemanager.api.response.CategoriaResponseDTO;
import com.cafemanager.cafemanager.application.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public CategoriaResponseDTO crear(
            @Valid @RequestBody CategoriaRequestDTO dto){

        return categoriaService.guardar(dto);

    }

    @GetMapping
    public List<CategoriaResponseDTO> listar(){

        return categoriaService.listar();

    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscar(
            @PathVariable Long id){

        return categoriaService.buscarPorId(id);

    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id){

        categoriaService.eliminar(id);

    }

}