package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByActivoTrue();

    boolean existsByNombreIgnoreCase(String nombre);

}