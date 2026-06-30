package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.PerdidaRequestDTO;
import com.cafemanager.cafemanager.api.response.PerdidaResponseDTO;
import com.cafemanager.cafemanager.application.mapper.PerdidaMapper;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.domain.entity.Perdida;
import com.cafemanager.cafemanager.domain.enums.TipoMovimientoStock;
import com.cafemanager.cafemanager.domain.repository.IngredienteRepository;
import com.cafemanager.cafemanager.domain.repository.PerdidaRepository;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PerdidaService {


    private final PerdidaRepository perdidaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final StockService stockService;


    public PerdidaService(
            PerdidaRepository perdidaRepository,
            IngredienteRepository ingredienteRepository,
            StockService stockService
    ) {

        this.perdidaRepository = perdidaRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.stockService = stockService;
    }


    @Transactional
    public PerdidaResponseDTO registrarPerdida(
            PerdidaRequestDTO dto
    ) {

        Ingrediente ingrediente =
                ingredienteRepository.findById(dto.getIngredienteId())
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "Ingrediente no encontrado"
                                )
                        );


        stockService.descontarStock(
                ingrediente,
                dto.getCantidad(),
                TipoMovimientoStock.PERDIDA,
                dto.getObservacion(),
                "PERDIDA"
        );


        Perdida perdida = new Perdida();

        perdida.setIngrediente(ingrediente);
        perdida.setCantidad(dto.getCantidad());
        perdida.setMotivo(dto.getMotivo());
        perdida.setFecha(LocalDateTime.now());
        perdida.setObservacion(dto.getObservacion());


        Perdida guardada =
                perdidaRepository.save(perdida);


        return PerdidaMapper.toResponse(guardada);
    }



    public List<PerdidaResponseDTO> listar() {

        return perdidaRepository.findAll()
                .stream()
                .map(PerdidaMapper::toResponse)
                .toList();
    }



    public PerdidaResponseDTO buscarPorId(Long id) {

        Perdida perdida =
                perdidaRepository.findById(id)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "Pérdida no encontrada"
                                )
                        );


        return PerdidaMapper.toResponse(perdida);
    }

}