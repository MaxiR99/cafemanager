package com.cafemanager.cafemanager.application.service;

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


    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }


    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }


    public Proveedor buscarPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Proveedor no encontrado"));
    }

}