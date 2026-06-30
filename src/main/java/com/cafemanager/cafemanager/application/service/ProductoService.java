package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.ProductoRequestDTO;
import com.cafemanager.cafemanager.api.response.ProductoResponseDTO;
import com.cafemanager.cafemanager.application.mapper.ProductoMapper;
import com.cafemanager.cafemanager.domain.entity.Categoria;
import com.cafemanager.cafemanager.domain.entity.Producto;
import com.cafemanager.cafemanager.domain.repository.CategoriaRepository;
import com.cafemanager.cafemanager.domain.repository.ProductoRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ProductoResponseDTO guardar(ProductoRequestDTO dto){

        Categoria categoria = obtenerCategoria(dto.getCategoriaId());

        Producto producto = ProductoMapper.toEntity(dto, categoria);

        Producto guardado = productoRepository.save(producto);

        return ProductoMapper.toResponse(guardado);
    }

    public List<ProductoResponseDTO> listar(){

        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

    public ProductoResponseDTO buscarPorId(Long id){

        return ProductoMapper.toResponse(
                obtenerProducto(id)
        );
    }

    @Transactional
    public void eliminar(Long id){

        Producto producto = obtenerProducto(id);

        producto.setActivo(false);

        productoRepository.save(producto);

    }

    private Producto obtenerProducto(Long id){

        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Producto no encontrado"));

    }

    private Categoria obtenerCategoria(Long id){

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Categoría no encontrada"));

    }

}