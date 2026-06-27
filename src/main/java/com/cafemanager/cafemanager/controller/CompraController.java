package com.cafemanager.cafemanager.controller;

import com.cafemanager.cafemanager.dto.CompraRequestDTO;
import com.cafemanager.cafemanager.dto.CompraResumenResponseDTO;
import com.cafemanager.cafemanager.entity.Compra;
import com.cafemanager.cafemanager.service.CompraService;
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

}