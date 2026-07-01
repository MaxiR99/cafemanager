package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Perdida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PerdidaRepository extends JpaRepository<Perdida, Long> {


    @Query("""
            SELECT COUNT(p)
            FROM Perdida p
            WHERE p.fecha >= :inicio
            AND p.fecha < :fin
            """)
    Long contarPerdidasEntreFechas(
            LocalDateTime inicio,
            LocalDateTime fin
    );



    @Query("""
            SELECT COALESCE(SUM(p.cantidad * p.ingrediente.costoCompra), 0)
            FROM Perdida p
            WHERE p.fecha >= :inicio
            AND p.fecha < :fin
            """)
    BigDecimal calcularCostoPerdidasEntreFechas(
            LocalDateTime inicio,
            LocalDateTime fin
    );

}