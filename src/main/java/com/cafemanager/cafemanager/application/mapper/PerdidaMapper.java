package com.cafemanager.cafemanager.application.mapper;

import com.cafemanager.cafemanager.api.response.PerdidaResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Perdida;

public class PerdidaMapper {


    public static PerdidaResponseDTO toResponse(Perdida perdida){

        return new PerdidaResponseDTO(
                perdida.getId(),
                perdida.getIngrediente().getId(),
                perdida.getIngrediente().getNombre(),
                perdida.getCantidad(),
                perdida.getMotivo().name(),
                perdida.getFecha(),
                perdida.getObservacion(),
                perdida.getUsuario() != null
                        ? perdida.getUsuario().getNombre()
                        : null
        );

    }

}
