package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.response.MovimientoStockResponseDTO;
import com.cafemanager.cafemanager.application.mapper.MovimientoStockMapper;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.entity.MovimientoStock;
import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import com.cafemanager.cafemanager.domain.repository.MovimientoStockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoStockService {

    private final MovimientoStockRepository movimientoStockRepository;

    public MovimientoStockService(
            MovimientoStockRepository movimientoStockRepository) {

        this.movimientoStockRepository = movimientoStockRepository;
    }

    public List<MovimientoStockResponseDTO> listar() {

        return movimientoStockRepository.findAll()
                .stream()
                .map(MovimientoStockMapper::toResponse)
                .toList();

    }

    public MovimientoStockResponseDTO registrarMovimiento(
            Ingrediente ingrediente,
            TipoMovimientoStock tipoMovimiento,
            BigDecimal cantidad,
            String observacion,
            String referencia
    ) {

        MovimientoStock movimiento = new MovimientoStock();

        movimiento.setIngrediente(ingrediente);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setCantidad(cantidad);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setObservacion(observacion);
        movimiento.setReferencia(referencia);

        MovimientoStock guardado =
                movimientoStockRepository.save(movimiento);

        return MovimientoStockMapper.toResponse(guardado);
    }

}