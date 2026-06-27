package com.cafemanager.cafemanager.service;

import com.cafemanager.cafemanager.entity.Proveedor;
import com.cafemanager.cafemanager.repository.ProveedorRepository;
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
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

}