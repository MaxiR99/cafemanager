package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.ProveedorRequestDTO;
import com.cafemanager.cafemanager.api.response.ProveedorResponseDTO;
import com.cafemanager.cafemanager.application.mapper.ProveedorMapper;
import com.cafemanager.cafemanager.domain.entity.Proveedor;
import com.cafemanager.cafemanager.domain.repository.ProveedorRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public ProveedorResponseDTO guardar(ProveedorRequestDTO dto) {

        Proveedor proveedor = ProveedorMapper.toEntity(dto);

        Proveedor guardado = proveedorRepository.save(proveedor);

        return ProveedorMapper.toResponse(guardado);
    }

    public List<ProveedorResponseDTO> listarTodos() {

        return proveedorRepository.findAll()
                .stream()
                .map(ProveedorMapper::toResponse)
                .toList();
    }

    public ProveedorResponseDTO buscarPorId(Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Proveedor no encontrado"));

        return ProveedorMapper.toResponse(proveedor);
    }

}