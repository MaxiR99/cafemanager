package com.cafemanager.cafemanager.service;

import com.cafemanager.cafemanager.dto.CompraRequestDTO;
import com.cafemanager.cafemanager.dto.CompraResumenResponseDTO;
import com.cafemanager.cafemanager.dto.DetalleCompraRequestDTO;
import com.cafemanager.cafemanager.entity.*;
import com.cafemanager.cafemanager.enums.TipoMovimientoStock;
import com.cafemanager.cafemanager.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProveedorRepository proveedorRepository;
    private final IngredienteRepository ingredienteRepository;
    private final MovimientoStockRepository movimientoStockRepository;


    public CompraService(
            CompraRepository compraRepository,
            ProveedorRepository proveedorRepository,
            IngredienteRepository ingredienteRepository,
            MovimientoStockRepository movimientoStockRepository
    ) {
        this.compraRepository = compraRepository;
        this.proveedorRepository = proveedorRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.movimientoStockRepository = movimientoStockRepository;
    }



    public List<CompraResumenResponseDTO> listarTodas() {

        return compraRepository.findAll()
                .stream()
                .map(compra -> new CompraResumenResponseDTO(
                        compra.getId(),
                        compra.getProveedor().getNombre(),
                        compra.getFecha(),
                        compra.getTotal()
                ))
                .toList();

    }


    @Transactional
    public Compra registrarCompra(CompraRequestDTO dto) {


        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));


        Compra compra = new Compra();

        compra.setProveedor(proveedor);
        compra.setFecha(LocalDateTime.now());
        compra.setTotal(BigDecimal.ZERO);


        for (DetalleCompraRequestDTO detalleDTO : dto.getDetalles()) {


            Ingrediente ingrediente = ingredienteRepository.findById(
                    detalleDTO.getIngredienteId()
            ).orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));


            // Actualizamos stock
            ingrediente.setStockActual(
                    ingrediente.getStockActual()
                            .add(detalleDTO.getCantidad())
            );


            ingrediente.setCostoCompra(
                    detalleDTO.getPrecioUnitario()
            );


            ingredienteRepository.save(ingrediente);



            MovimientoStock movimiento = new MovimientoStock();

            movimiento.setIngrediente(ingrediente);
            movimiento.setTipoMovimiento(TipoMovimientoStock.COMPRA);
            movimiento.setCantidad(detalleDTO.getCantidad());
            movimiento.setFecha(LocalDateTime.now());
            movimiento.setReferencia("Compra");

            movimientoStockRepository.save(movimiento);



            DetalleCompra detalle = new DetalleCompra();

            detalle.setCompra(compra);
            detalle.setIngrediente(ingrediente);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalle.setFechaVencimiento(detalleDTO.getFechaVencimiento());


            compra.getDetalles().add(detalle);


            compra.setTotal(
                    compra.getTotal()
                            .add(
                                    detalleDTO.getCantidad()
                                            .multiply(detalleDTO.getPrecioUnitario())
                            )
            );
        }


        return compraRepository.save(compra);
    }

}