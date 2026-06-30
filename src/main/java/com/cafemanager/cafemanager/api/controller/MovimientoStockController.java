package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.MovimientoStockRequestDTO;
import com.cafemanager.cafemanager.api.response.MovimientoStockResponseDTO;
import com.cafemanager.cafemanager.application.service.MovimientoStockService;
import com.cafemanager.cafemanager.application.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos-stock")
public class MovimientoStockController {

    private final StockService stockService;
    private final MovimientoStockService movimientoStockService;

    public MovimientoStockController(
            StockService stockService,
            MovimientoStockService movimientoStockService) {

        this.stockService = stockService;
        this.movimientoStockService = movimientoStockService;
    }

    @GetMapping
    public List<MovimientoStockResponseDTO> listar() {
        return movimientoStockService.listar();
    }

    @PostMapping
    public ResponseEntity<MovimientoStockResponseDTO> registrarAjuste(
            @Valid @RequestBody MovimientoStockRequestDTO dto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stockService.registrarAjuste(dto));
    }

}