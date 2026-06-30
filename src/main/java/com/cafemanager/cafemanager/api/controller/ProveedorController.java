package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.ProveedorRequestDTO;
import com.cafemanager.cafemanager.api.response.ProveedorResponseDTO;
import com.cafemanager.cafemanager.application.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProveedorResponseDTO> crear(
            @Valid @RequestBody ProveedorRequestDTO dto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(proveedorService.guardar(dto));
    }

    @GetMapping
    public List<ProveedorResponseDTO> listar() {
        return proveedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProveedorResponseDTO buscar(@PathVariable Long id) {
        return proveedorService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){

        proveedorService.eliminar(id);

    }

}