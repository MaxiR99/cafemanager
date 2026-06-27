package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}