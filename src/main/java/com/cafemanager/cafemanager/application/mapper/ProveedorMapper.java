package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.request.ProveedorRequestDTO;
import com.cafemanager.cafemanager.api.response.ProveedorResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Proveedor;

public class ProveedorMapper {

    private ProveedorMapper() {
    }

    public static Proveedor toEntity(ProveedorRequestDTO dto) {

        Proveedor proveedor = new Proveedor();

        proveedor.setNombre(dto.getNombre());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setEmail(dto.getEmail());
        proveedor.setDireccion(dto.getDireccion());


        return proveedor;
    }

    public static ProveedorResponseDTO toResponse(Proveedor proveedor) {

        return new ProveedorResponseDTO(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getTelefono(),
                proveedor.getEmail(),
                proveedor.getDireccion(),
                proveedor.getActivo()
        );
    }

}