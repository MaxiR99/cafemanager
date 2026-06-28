package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.DetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
}