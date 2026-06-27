package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.domain.entity.Proveedor;
import com.cafemanager.cafemanager.application.service.ProveedorService;
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
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return proveedorService.guardar(proveedor);
    }


    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.listarTodos();
    }


    @GetMapping("/{id}")
    public Proveedor buscar(@PathVariable Long id) {
        return proveedorService.buscarPorId(id);
    }

}