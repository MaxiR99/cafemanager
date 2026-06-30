package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.ProveedorRequestDTO;
import com.cafemanager.cafemanager.api.response.ProveedorResponseDTO;
import com.cafemanager.cafemanager.application.mapper.ProveedorMapper;
import com.cafemanager.cafemanager.domain.entity.Proveedor;
import com.cafemanager.cafemanager.domain.repository.ProveedorRepository;
import com.cafemanager.cafemanager.exception.RecursoDuplicadoException;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Transactional
    public ProveedorResponseDTO guardar(ProveedorRequestDTO dto){

        if(proveedorRepository
                .existsByNombreIgnoreCaseAndActivoTrue(dto.getNombre())){

            throw new RecursoDuplicadoException(
                    "El proveedor ya existe");
        }

        Proveedor proveedor = ProveedorMapper.toEntity(dto);

        return ProveedorMapper.toResponse(
                proveedorRepository.save(proveedor)
        );
    }

    public List<ProveedorResponseDTO> listarTodos() {

        return proveedorRepository.findByActivoTrue()
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

    @Transactional
    public void eliminar(Long id){

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Proveedor no encontrado"));

        proveedor.setActivo(false);
    }

}