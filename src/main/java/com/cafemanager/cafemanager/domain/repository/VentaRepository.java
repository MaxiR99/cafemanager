package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VentaRepository extends JpaRepository<Venta, Long> {


    @Query("""
            SELECT COUNT(v)
            FROM Venta v
            WHERE v.fecha >= :inicio
            AND v.fecha < :fin
            """)
    Long contarVentasEntreFechas(
            LocalDateTime inicio,
            LocalDateTime fin
    );



    @Query("""
            SELECT COALESCE(SUM(v.total), 0)
            FROM Venta v
            WHERE v.fecha >= :inicio
            AND v.fecha < :fin
            """)
    BigDecimal sumarVentasEntreFechas(
            LocalDateTime inicio,
            LocalDateTime fin
    );

}