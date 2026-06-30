package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.PerdidaRequestDTO;
import com.cafemanager.cafemanager.api.response.PerdidaResponseDTO;
import com.cafemanager.cafemanager.application.service.PerdidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perdidas")
public class PerdidaController {


    private final PerdidaService perdidaService;


    public PerdidaController(
            PerdidaService perdidaService
    ) {

        this.perdidaService = perdidaService;
    }


    @PostMapping
    public ResponseEntity<PerdidaResponseDTO> registrar(
            @Valid @RequestBody PerdidaRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(perdidaService.registrarPerdida(dto));
    }



    @GetMapping
    public ResponseEntity<List<PerdidaResponseDTO>> listar() {

        return ResponseEntity.ok(
                perdidaService.listar()
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<PerdidaResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                perdidaService.buscarPorId(id)
        );
    }

}