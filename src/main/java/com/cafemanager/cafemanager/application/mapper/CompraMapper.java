package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.CompraDetalleResponseDTO;
import com.cafemanager.cafemanager.api.response.CompraResumenResponseDTO;
import com.cafemanager.cafemanager.api.response.DetalleCompraResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Compra;
import com.cafemanager.cafemanager.domain.entity.DetalleCompra;

import java.util.List;

public class CompraMapper {

    private CompraMapper() {
    }

    public static CompraResumenResponseDTO toResumenDTO(Compra compra) {

        return new CompraResumenResponseDTO(
                compra.getId(),
                compra.getProveedor().getNombre(),
                compra.getFecha(),
                compra.getTotal()
        );

    }

    public static CompraDetalleResponseDTO toDetalleDTO(Compra compra) {

        List<DetalleCompraResponseDTO> detalles =
                compra.getDetalles()
                        .stream()
                        .map(CompraMapper::toDetalleDTO)
                        .toList();

        return new CompraDetalleResponseDTO(
                compra.getId(),
                compra.getProveedor().getNombre(),
                compra.getFecha(),
                compra.getTotal(),
                detalles
        );

    }

    private static DetalleCompraResponseDTO toDetalleDTO(
            DetalleCompra detalle
    ) {

        return new DetalleCompraResponseDTO(
                detalle.getIngrediente().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getFechaVencimiento()
        );

    }

}
