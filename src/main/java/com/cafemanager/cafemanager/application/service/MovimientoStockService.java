package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.entity.MovimientoStock;
import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import com.cafemanager.cafemanager.domain.repository.MovimientoStockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class MovimientoStockService {


    private final MovimientoStockRepository movimientoStockRepository;


    public MovimientoStockService(
            MovimientoStockRepository movimientoStockRepository
    ) {
        this.movimientoStockRepository = movimientoStockRepository;
    }



    public void registrarSalida(
            Ingrediente ingrediente,
            BigDecimal cantidad
    ) {


        MovimientoStock movimiento =
                new MovimientoStock();


        movimiento.setIngrediente(ingrediente);


        movimiento.setTipoMovimiento(
                TipoMovimientoStock.VENTA
        );


        movimiento.setCantidad(cantidad);


        movimiento.setFecha(
                LocalDateTime.now()
        );


        movimiento.setReferencia(
                "VENTA"
        );


        movimiento.setObservacion(
                "Descuento automático por venta"
        );


        movimientoStockRepository.save(movimiento);
    }

}