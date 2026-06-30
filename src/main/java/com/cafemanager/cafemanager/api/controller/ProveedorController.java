package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.ProveedorRequestDTO;
import com.cafemanager.cafemanager.api.response.ProveedorResponseDTO;
import com.cafemanager.cafemanager.application.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ProveedorResponseDTO crear(@Valid @RequestBody ProveedorRequestDTO dto) {
        return proveedorService.guardar(dto);
    }

    @GetMapping
    public List<ProveedorResponseDTO> listar() {
        return proveedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProveedorResponseDTO buscar(@PathVariable Long id) {
        return proveedorService.buscarPorId(id);
    }

}