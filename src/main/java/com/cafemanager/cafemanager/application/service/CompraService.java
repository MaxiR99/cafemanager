package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.CompraRequestDTO;
import com.cafemanager.cafemanager.api.request.DetalleCompraRequestDTO;
import com.cafemanager.cafemanager.api.response.CompraDetalleResponseDTO;
import com.cafemanager.cafemanager.api.response.CompraResumenResponseDTO;
import com.cafemanager.cafemanager.application.mapper.CompraMapper;
import com.cafemanager.cafemanager.domain.entity.*;
import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import com.cafemanager.cafemanager.domain.repository.CompraRepository;
import com.cafemanager.cafemanager.domain.repository.IngredienteRepository;
import com.cafemanager.cafemanager.domain.repository.MovimientoStockRepository;
import com.cafemanager.cafemanager.domain.repository.ProveedorRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import com.cafemanager.cafemanager.exception.ReglaNegocioException;
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
            MovimientoStockRepository movimientoStockRepository) {

        this.compraRepository = compraRepository;
        this.proveedorRepository = proveedorRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.movimientoStockRepository = movimientoStockRepository;
    }

    public List<CompraResumenResponseDTO> listarTodas() {

        return compraRepository.findAll()
                .stream()
                .map(CompraMapper::toResumenDTO)
                .toList();
    }

    public CompraDetalleResponseDTO buscarDetalle(Long id) {

        return CompraMapper.toDetalleDTO(
                obtenerCompra(id)
        );
    }

    @Transactional
    public CompraDetalleResponseDTO registrarCompra(CompraRequestDTO dto) {

        Proveedor proveedor = obtenerProveedor(dto.getProveedorId());

        Compra compra = new Compra();
        compra.setProveedor(proveedor);
        compra.setFecha(LocalDateTime.now());
        compra.setTotal(BigDecimal.ZERO);

        for (DetalleCompraRequestDTO detalleDTO : dto.getDetalles()) {

            Ingrediente ingrediente =
                    obtenerIngrediente(detalleDTO.getIngredienteId());

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

            BigDecimal subtotal = detalleDTO.getCantidad()
                    .multiply(detalleDTO.getPrecioUnitario());

            compra.setTotal(
                    compra.getTotal().add(subtotal)
            );
        }

        Compra compraGuardada = compraRepository.save(compra);

        return CompraMapper.toDetalleDTO(compraGuardada);
    }

    private Compra obtenerCompra(Long id) {

        return compraRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Compra no encontrada"));
    }

    private Proveedor obtenerProveedor(Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Proveedor no encontrado"));

        if (!proveedor.getActivo()) {
            throw new ReglaNegocioException("El proveedor está inactivo");
        }

        return proveedor;
    }

    private Ingrediente obtenerIngrediente(Long id) {

        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ingrediente no encontrado"));

        if (!ingrediente.getActivo()) {
            throw new ReglaNegocioException("El ingrediente está inactivo");
        }

        return ingrediente;
    }

}