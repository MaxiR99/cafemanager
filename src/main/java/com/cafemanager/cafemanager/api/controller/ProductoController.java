package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.ProductoRequestDTO;
import com.cafemanager.cafemanager.api.response.ProductoResponseDTO;
import com.cafemanager.cafemanager.application.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ProductoResponseDTO crear(
            @Valid @RequestBody ProductoRequestDTO dto){

        return productoService.guardar(dto);

    }

    @GetMapping
    public List<ProductoResponseDTO> listar(){

        return productoService.listar();

    }

    @GetMapping("/{id}")
    public ProductoResponseDTO buscar(
            @PathVariable Long id){

        return productoService.buscarPorId(id);

    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id){

        productoService.eliminar(id);

    }

}