package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.api.response.IngredienteStockBajoResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {


    List<Ingrediente> findByActivoTrue();

    boolean existsByNombreIgnoreCaseAndActivoTrue(String nombre);

    List<Ingrediente> findByStockActualLessThanEqual(BigDecimal stock);

    @Query("""
       SELECT i
       FROM Ingrediente i
       WHERE i.stockActual <= i.stockMinimo
       AND i.activo = true
       """)
    List<Ingrediente> buscarStockBajo();

    @Query("""
        SELECT new com.cafemanager.cafemanager.api.response.IngredienteStockBajoResponseDTO(
            i.id,
            i.nombre,
            i.stockActual,
            i.stockMinimo,
            (i.stockMinimo - i.stockActual),
            i.unidadMedida
        )
        FROM Ingrediente i
        WHERE i.stockActual <= i.stockMinimo
        AND i.activo = true
        """)
    List<IngredienteStockBajoResponseDTO> listarIngredientesStockBajo();

}


