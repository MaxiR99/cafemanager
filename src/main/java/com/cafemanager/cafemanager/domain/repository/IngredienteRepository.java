package com.cafemanager.cafemanager.domain.repository;

import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
