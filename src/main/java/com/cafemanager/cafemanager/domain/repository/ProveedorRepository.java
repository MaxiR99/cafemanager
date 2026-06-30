package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByActivoTrue();
    boolean existsByNombreIgnoreCaseAndActivoTrue(String nombre);
}