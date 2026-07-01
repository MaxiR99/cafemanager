package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.api.response.ProductoMasVendidoDTO;
import com.cafemanager.cafemanager.domain.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {


    @Query("""
            SELECT COALESCE(SUM(d.cantidad), 0)
            FROM DetalleVenta d
            WHERE d.venta.fecha >= :inicio
            AND d.venta.fecha < :fin
            """)
    Long sumarProductosVendidosEntreFechas(
            LocalDateTime inicio,
            LocalDateTime fin
    );



    @Query("""
            SELECT new com.cafemanager.cafemanager.api.response.ProductoMasVendidoDTO(
                d.producto.nombre,
                SUM(d.cantidad)
            )
            FROM DetalleVenta d
            WHERE d.venta.fecha >= :inicio
            AND d.venta.fecha < :fin
            GROUP BY d.producto.nombre
            ORDER BY SUM(d.cantidad) DESC
            LIMIT 1
            """)
    Optional<ProductoMasVendidoDTO> buscarProductoMasVendido(
            LocalDateTime inicio,
            LocalDateTime fin
    );

}