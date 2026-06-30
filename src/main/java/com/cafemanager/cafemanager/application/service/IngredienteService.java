package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.IngredienteRequestDTO;
import com.cafemanager.cafemanager.api.response.IngredienteResponseDTO;
import com.cafemanager.cafemanager.api.response.IngredienteStockBajoResponseDTO;
import com.cafemanager.cafemanager.application.mapper.IngredienteMapper;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.repository.IngredienteRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<IngredienteResponseDTO> listarTodos() {

        return ingredienteRepository.findAll()
                .stream()
                .map(IngredienteMapper::toResponse)
                .toList();
    }

    public IngredienteResponseDTO guardar(IngredienteRequestDTO dto) {

        Ingrediente ingrediente = IngredienteMapper.toEntity(dto);

        Ingrediente guardado = ingredienteRepository.save(ingrediente);

        return IngredienteMapper.toResponse(guardado);
    }

    public IngredienteResponseDTO buscarPorId(Long id) {

        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ingrediente no encontrado"));

        return IngredienteMapper.toResponse(ingrediente);
    }

    public void eliminar(Long id) {

        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ingrediente no encontrado"));

        ingredienteRepository.delete(ingrediente);
    }

    public List<IngredienteStockBajoResponseDTO> listarStockBajo() {

        return ingredienteRepository.buscarStockBajo()
                .stream()
                .map(IngredienteMapper::toStockBajoResponse)
                .toList();
    }

}