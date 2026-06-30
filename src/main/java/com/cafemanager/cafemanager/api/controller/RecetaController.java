package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.RecetaRequestDTO;
import com.cafemanager.cafemanager.api.response.RecetaResponseDTO;
import com.cafemanager.cafemanager.application.service.RecetaService;
import com.cafemanager.cafemanager.domain.entity.Receta;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> crear(
           @Valid @RequestBody RecetaRequestDTO dto
    ) {

        RecetaResponseDTO receta = recetaService.registrarReceta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(receta);
    }


    @GetMapping
    public List<RecetaResponseDTO> listar() {
        return recetaService.listar();
    }

    @GetMapping("/{id}")
    public RecetaResponseDTO buscar(
            @PathVariable Long id) {

        return recetaService.buscarPorId(id);

    }

}