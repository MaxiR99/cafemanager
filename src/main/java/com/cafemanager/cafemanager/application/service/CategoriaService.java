package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.CategoriaRequestDTO;
import com.cafemanager.cafemanager.api.response.CategoriaResponseDTO;
import com.cafemanager.cafemanager.application.mapper.CategoriaMapper;
import com.cafemanager.cafemanager.domain.entity.Categoria;
import com.cafemanager.cafemanager.domain.repository.CategoriaRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto){

        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setActivo(true);

        return CategoriaMapper.toResponse(
                categoriaRepository.save(categoria)
        );

    }

    public List<CategoriaResponseDTO> listar(){

        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toResponse)
                .toList();

    }

    public CategoriaResponseDTO buscarPorId(Long id){

        return CategoriaMapper.toResponse(
                obtenerCategoria(id)
        );

    }

    @Transactional
    public void eliminar(Long id){

        Categoria categoria = obtenerCategoria(id);

        categoria.setActivo(false);

        categoriaRepository.save(categoria);

    }

    private Categoria obtenerCategoria(Long id){

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Categoría no encontrada"));

    }

}
