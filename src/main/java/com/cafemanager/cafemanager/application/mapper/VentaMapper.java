package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.DetalleVentaResponseDTO;
import com.cafemanager.cafemanager.api.response.VentaResponseDTO;
import com.cafemanager.cafemanager.domain.entity.DetalleVenta;
import com.cafemanager.cafemanager.domain.entity.Venta;

import java.util.List;

public class VentaMapper {

    public static VentaResponseDTO toResponse(Venta venta){

        List<DetalleVentaResponseDTO> detalles =
                venta.getDetalles()
                        .stream()
                        .map(VentaMapper::toDetalleResponse)
                        .toList();

        return new VentaResponseDTO(
                venta.getId(),
                venta.getFecha(),
                venta.getTotal(),
                venta.getEstado(),
                detalles
        );

    }

    private static DetalleVentaResponseDTO toDetalleResponse(
            DetalleVenta detalle){

        return new DetalleVentaResponseDTO(
                detalle.getProducto().getId(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
        );

    }

}