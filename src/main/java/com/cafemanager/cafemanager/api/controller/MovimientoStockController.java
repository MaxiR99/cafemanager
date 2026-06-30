package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.MovimientoStockRequestDTO;
import com.cafemanager.cafemanager.api.response.MovimientoStockResponseDTO;
import com.cafemanager.cafemanager.application.mapper.MovimientoStockMapper;
import com.cafemanager.cafemanager.application.service.StockService;
import com.cafemanager.cafemanager.domain.entity.MovimientoStock;
import com.cafemanager.cafemanager.domain.repository.MovimientoStockRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos-stock")
public class MovimientoStockController {


    private final MovimientoStockRepository movimientoStockRepository;
    private final StockService stockService;


    public MovimientoStockController(
            MovimientoStockRepository movimientoStockRepository,
            StockService stockService
    ) {
        this.movimientoStockRepository = movimientoStockRepository;
        this.stockService = stockService;
    }


    @GetMapping
    public List<MovimientoStockResponseDTO> listarMovimientos() {

        return movimientoStockRepository.findAll()
                .stream()
                .map(MovimientoStockMapper::toResponse)
                .toList();

    }

    @PostMapping("/ajuste")
    public void registrarAjuste(
            @Valid @RequestBody MovimientoStockRequestDTO dto
    ) {

        stockService.registrarAjuste(dto);

    }
}