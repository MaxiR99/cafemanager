package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.DetalleRecetaRequestDTO;
import com.cafemanager.cafemanager.api.request.RecetaRequestDTO;
import com.cafemanager.cafemanager.api.response.RecetaResponseDTO;
import com.cafemanager.cafemanager.application.mapper.RecetaMapper;
import com.cafemanager.cafemanager.domain.entity.DetalleReceta;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.entity.Producto;
import com.cafemanager.cafemanager.domain.entity.Receta;
import com.cafemanager.cafemanager.domain.repository.IngredienteRepository;
import com.cafemanager.cafemanager.domain.repository.ProductoRepository;
import com.cafemanager.cafemanager.domain.repository.RecetaRepository;
import com.cafemanager.cafemanager.exception.ReglaNegocioException;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final ProductoRepository productoRepository;
    private final IngredienteRepository ingredienteRepository;

    public RecetaService(
            RecetaRepository recetaRepository,
            ProductoRepository productoRepository,
            IngredienteRepository ingredienteRepository
    ) {
        this.recetaRepository = recetaRepository;
        this.productoRepository = productoRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @Transactional
    public RecetaResponseDTO registrarReceta(RecetaRequestDTO dto) {

        if (recetaRepository.findByProductoId(dto.getProductoId()).isPresent()) {
            throw new ReglaNegocioException(
                    "El producto ya tiene una receta registrada"
            );
        }

        Producto producto = obtenerProducto(dto.getProductoId());

        Receta receta = new Receta();
        receta.setProducto(producto);
        receta.setActiva(true);

        for (DetalleRecetaRequestDTO detalleDTO : dto.getDetalles()) {

            Ingrediente ingrediente =
                    obtenerIngrediente(detalleDTO.getIngredienteId());

            DetalleReceta detalle = new DetalleReceta();
            detalle.setReceta(receta);
            detalle.setIngrediente(ingrediente);
            detalle.setCantidad(detalleDTO.getCantidad());

            receta.getDetalles().add(detalle);
        }

        Receta recetaGuardada = recetaRepository.save(receta);

        return RecetaMapper.toResponse(recetaGuardada);
    }

    private Producto obtenerProducto(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Producto no encontrado"));

        if (!producto.getActivo()) {
            throw new ReglaNegocioException("El producto está inactivo");
        }

        return producto;
    }

    private Ingrediente obtenerIngrediente(Long id) {

        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ingrediente no encontrado"));

        if (!ingrediente.getActivo()) {
            throw new ReglaNegocioException("El ingrediente está inactivo");
        }

        return ingrediente;
    }

    public List<RecetaResponseDTO> listar() {

        return recetaRepository.findAll()
                .stream()
                .map(RecetaMapper::toResponse)
                .toList();

    }

    public RecetaResponseDTO buscarPorId(Long id) {

        return RecetaMapper.toResponse(
                obtenerReceta(id)
        );

    }

    private Receta obtenerReceta(Long id) {

        return recetaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Receta no encontrada"));
    }

}