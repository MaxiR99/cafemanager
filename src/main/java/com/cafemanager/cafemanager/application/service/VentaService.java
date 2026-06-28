package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.DetalleVentaRequestDTO;
import com.cafemanager.cafemanager.api.request.VentaRequestDTO;
import com.cafemanager.cafemanager.api.response.VentaResponseDTO;
import com.cafemanager.cafemanager.application.mapper.VentaMapper;
import com.cafemanager.cafemanager.domain.entity.DetalleVenta;
import com.cafemanager.cafemanager.domain.entity.Producto;
import com.cafemanager.cafemanager.domain.entity.Venta;
import com.cafemanager.cafemanager.domain.enums.EstadoVenta;
import com.cafemanager.cafemanager.domain.repository.ProductoRepository;
import com.cafemanager.cafemanager.domain.repository.VentaRepository;
import com.cafemanager.cafemanager.exception.ProductoNoDisponibleException;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final StockService stockService;

    public VentaService(
            VentaRepository ventaRepository,
            ProductoRepository productoRepository,
            StockService stockService) {

        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.stockService = stockService;
    }

    @Transactional
    public VentaResponseDTO registrarVenta(VentaRequestDTO dto) {

        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setEstado(EstadoVenta.COMPLETADA);
        venta.setTotal(BigDecimal.ZERO);

        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVentaRequestDTO detalleDTO : dto.getDetalles()) {

            Producto producto = obtenerProducto(detalleDTO.getProductoId());

            stockService.descontarIngredientes(
                    producto,
                    detalleDTO.getCantidad());

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            BigDecimal subtotal = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(detalleDTO.getCantidad()));

            detalle.setSubtotal(subtotal);

            venta.getDetalles().add(detalle);

            total = total.add(subtotal);
        }

        venta.setTotal(total);

        Venta ventaGuardada = ventaRepository.save(venta);

        return VentaMapper.toResponse(ventaGuardada);
    }

    private Producto obtenerProducto(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Producto no encontrado"));

        if (!producto.getActivo()) {
            throw new ProductoNoDisponibleException("El producto está inactivo");
        }

        if (!producto.getDisponible()) {
            throw new ProductoNoDisponibleException("El producto no está disponible");
        }

        return producto;
    }

}