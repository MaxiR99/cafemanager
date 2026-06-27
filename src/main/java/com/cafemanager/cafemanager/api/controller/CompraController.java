package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.CompraRequestDTO;
import com.cafemanager.cafemanager.api.response.CompraDetalleResponseDTO;
import com.cafemanager.cafemanager.api.response.CompraResumenResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Compra;
import com.cafemanager.cafemanager.application.service.CompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {


    private final CompraService compraService;


    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }


    @PostMapping
    public ResponseEntity<Compra> registrarCompra(
            @RequestBody CompraRequestDTO compraDTO
    ){

        Compra compra = compraService.registrarCompra(compraDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(compra);
    }

    @GetMapping
    public List<CompraResumenResponseDTO> listar() {
        return compraService.listarTodas();
    }

    @GetMapping("/{id}")
    public CompraDetalleResponseDTO buscarDetalle(
            @PathVariable Long id
    ){

        return compraService.buscarDetalle(id);

    }

}