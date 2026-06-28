package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.VentaRequestDTO;
import com.cafemanager.cafemanager.api.response.VentaResponseDTO;
import com.cafemanager.cafemanager.application.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> registrar(
            @Valid @RequestBody VentaRequestDTO dto) {

        VentaResponseDTO response = ventaService.registrarVenta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
