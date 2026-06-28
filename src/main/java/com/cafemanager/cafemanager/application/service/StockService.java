package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.domain.entity.DetalleReceta;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.entity.Producto;
import com.cafemanager.cafemanager.domain.entity.Receta;
import com.cafemanager.cafemanager.domain.repository.RecetaRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import com.cafemanager.cafemanager.exception.StockInsuficienteException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StockService {

    private final RecetaRepository recetaRepository;
    private final MovimientoStockService movimientoStockService;


    public StockService(
            RecetaRepository recetaRepository,
            MovimientoStockService movimientoStockService
    ) {
        this.recetaRepository = recetaRepository;
        this.movimientoStockService = movimientoStockService;
    }


    public void descontarIngredientes(
            Producto producto,
            Integer cantidadVendida
    ) {


        Receta receta = recetaRepository
                .findByProductoId(producto.getId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "El producto no tiene receta asociada"
                        )
                );


        if (!receta.getActiva()) {
            throw new IllegalStateException(
                    "La receta del producto está inactiva"
            );
        }


        for (DetalleReceta detalle : receta.getDetalles()) {


            Ingrediente ingrediente =
                    detalle.getIngrediente();


            BigDecimal cantidadNecesaria =
                    detalle.getCantidad()
                            .multiply(
                                    BigDecimal.valueOf(cantidadVendida)
                            );


            validarStock(
                    ingrediente,
                    cantidadNecesaria
            );


            ingrediente.setStockActual(
                    ingrediente.getStockActual()
                            .subtract(cantidadNecesaria)
            );


            movimientoStockService.registrarSalida(
                    ingrediente,
                    cantidadNecesaria
            );
        }
    }



    private void validarStock(
            Ingrediente ingrediente,
            BigDecimal cantidadNecesaria
    ) {


        if (ingrediente.getStockActual()
                .compareTo(cantidadNecesaria) < 0) {


            throw new StockInsuficienteException(
                    "Stock insuficiente del ingrediente: "
                            + ingrediente.getNombre()
            );
        }
    }
}