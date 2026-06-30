package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {


    List<Ingrediente> findByActivoTrue();

    boolean existsByNombreIgnoreCase(String nombre);

    List<Ingrediente> findByStockActualLessThanEqual(BigDecimal stock);

    @Query("""
       SELECT i
       FROM Ingrediente i
       WHERE i.stockActual <= i.stockMinimo
       AND i.activo = true
       """)
    List<Ingrediente> buscarStockBajo();

}


