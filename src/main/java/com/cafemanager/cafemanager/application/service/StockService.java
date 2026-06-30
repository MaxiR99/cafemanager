package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.MovimientoStockRequestDTO;
import com.cafemanager.cafemanager.api.response.MovimientoStockResponseDTO;
import com.cafemanager.cafemanager.domain.entity.DetalleReceta;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.entity.Producto;
import com.cafemanager.cafemanager.domain.entity.Receta;
import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import com.cafemanager.cafemanager.domain.repository.IngredienteRepository;
import com.cafemanager.cafemanager.domain.repository.RecetaRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import com.cafemanager.cafemanager.exception.ReglaNegocioException;
import com.cafemanager.cafemanager.exception.StockInsuficienteException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StockService {

    private final RecetaRepository recetaRepository;
    private final MovimientoStockService movimientoStockService;
    private final IngredienteRepository ingredienteRepository;


    public StockService(
            RecetaRepository recetaRepository,
            MovimientoStockService movimientoStockService,
            IngredienteRepository ingredienteRepository
    ) {
        this.recetaRepository = recetaRepository;
        this.movimientoStockService = movimientoStockService;
        this.ingredienteRepository = ingredienteRepository;
    }


    public void descontarIngredientes(
            Producto producto,
            Integer cantidadVendida
    ) {


        Receta receta = recetaRepository
                .findByProductoIdAndActivaTrue(producto.getId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "El producto no tiene receta asociada"
                        )
                );





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


            movimientoStockService.registrarMovimiento(
                    ingrediente,
                    TipoMovimientoStock.VENTA,
                    cantidadNecesaria,
                    "Descuento automático por venta",
                    "VENTA"
            );
        }
    }

    @Transactional
    public void descontarStock(
            Ingrediente ingrediente,
            BigDecimal cantidad,
            TipoMovimientoStock tipoMovimiento,
            String observacion,
            String referencia
    ){

        validarStock(ingrediente, cantidad);


        ingrediente.setStockActual(
                ingrediente.getStockActual()
                        .subtract(cantidad)
        );


        ingredienteRepository.save(ingrediente);


        movimientoStockService.registrarMovimiento(
                ingrediente,
                tipoMovimiento,
                cantidad,
                observacion,
                referencia
        );
    }



    @Transactional
    public MovimientoStockResponseDTO registrarAjuste(MovimientoStockRequestDTO dto) {

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ingrediente no encontrado"));

        switch (dto.getTipoMovimiento()) {

            case AJUSTE -> ingrediente.setStockActual(
                    ingrediente.getStockActual()
                            .add(dto.getCantidad())
            );

            case PERDIDA -> {

                validarStock(
                        ingrediente,
                        dto.getCantidad()
                );

                ingrediente.setStockActual(
                        ingrediente.getStockActual()
                                .subtract(dto.getCantidad())
                );
            }

            default -> throw new ReglaNegocioException(
                    "Tipo de movimiento no permitido"
            );
        }



        ingredienteRepository.save(ingrediente);


        return movimientoStockService.registrarMovimiento(
                ingrediente,
                dto.getTipoMovimiento(),
                dto.getCantidad(),
                dto.getObservacion(),
                "AJUSTE"
        );
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