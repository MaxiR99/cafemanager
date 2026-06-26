package com.cafemanager.cafemanager.service;

import com.cafemanager.cafemanager.dto.IngredienteRequestDTO;
import com.cafemanager.cafemanager.entity.Ingrediente;
import com.cafemanager.cafemanager.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }


    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }


    public Ingrediente guardar(IngredienteRequestDTO dto) {
        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setNombre(dto.getNombre());
        ingrediente.setStockActual(dto.getStockActual());
        ingrediente.setStockMinimo(dto.getStockMinimo());
        ingrediente.setCostoCompra(dto.getCostoCompra());
        ingrediente.setUnidadMedida(dto.getUnidadMedida());
        ingrediente.setActivo(true);

        return ingredienteRepository.save(ingrediente);
    }


    public Ingrediente buscarPorId(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
    }


    public void eliminar(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
