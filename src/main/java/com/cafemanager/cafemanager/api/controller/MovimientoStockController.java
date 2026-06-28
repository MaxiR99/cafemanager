package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.response.MovimientoStockResponseDTO;
import com.cafemanager.cafemanager.application.mapper.MovimientoStockMapper;
import com.cafemanager.cafemanager.domain.entity.MovimientoStock;
import com.cafemanager.cafemanager.domain.repository.MovimientoStockRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos-stock")
public class MovimientoStockController {


    private final MovimientoStockRepository movimientoStockRepository;


    public MovimientoStockController(
            MovimientoStockRepository movimientoStockRepository
    ) {
        this.movimientoStockRepository = movimientoStockRepository;
    }


    @GetMapping
    public List<MovimientoStockResponseDTO> listarMovimientos() {

        return movimientoStockRepository.findAll()
                .stream()
                .map(MovimientoStockMapper::toResponse)
                .toList();

    }
}