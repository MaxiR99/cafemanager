package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.response.DashboardResponseDTO;
import com.cafemanager.cafemanager.api.response.IngredienteStockBajoResponseDTO;
import com.cafemanager.cafemanager.domain.repository.DetalleVentaRepository;
import com.cafemanager.cafemanager.domain.repository.IngredienteRepository;
import com.cafemanager.cafemanager.domain.repository.PerdidaRepository;
import com.cafemanager.cafemanager.domain.repository.VentaRepository;
import org.springframework.stereotype.Service;
import com.cafemanager.cafemanager.api.response.ProductoMasVendidoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DashboardService {


    private final VentaRepository ventaRepository;
    private final PerdidaRepository perdidaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final DetalleVentaRepository detalleVentaRepository;


    public DashboardService(
            VentaRepository ventaRepository,
            PerdidaRepository perdidaRepository,
            IngredienteRepository ingredienteRepository,
            DetalleVentaRepository detalleVentaRepository
    ) {

        this.ventaRepository = ventaRepository;
        this.perdidaRepository = perdidaRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }



    public DashboardResponseDTO obtenerDashboard() {


        LocalDateTime inicio =
                LocalDate.now()
                        .atStartOfDay();


        LocalDateTime fin =
                LocalDate.now()
                        .atTime(LocalTime.MAX);



        Long ventasHoy =
                ventaRepository.contarVentasEntreFechas(
                        inicio,
                        fin
                );


        BigDecimal ingresosHoy =
                ventaRepository.sumarVentasEntreFechas(
                        inicio,
                        fin
                );


        Long perdidasHoy =
                perdidaRepository.contarPerdidasEntreFechas(
                        inicio,
                        fin
                );


        BigDecimal costoPerdidasHoy =
                perdidaRepository.calcularCostoPerdidasEntreFechas(
                        inicio,
                        fin
                );

        Long productosVendidosHoy =
                detalleVentaRepository.sumarProductosVendidosEntreFechas(
                        inicio,
                        fin
                );


        ProductoMasVendidoDTO productoMasVendido =
                detalleVentaRepository.buscarProductoMasVendido(
                                inicio,
                                fin
                        )
                        .orElse(null);

        List<IngredienteStockBajoResponseDTO> ingredientesStockBajo =
                ingredienteRepository.listarIngredientesStockBajo();



        return new DashboardResponseDTO(
                ventasHoy,
                ingresosHoy,
                perdidasHoy,
                costoPerdidasHoy,
                productosVendidosHoy,
                productoMasVendido != null
                        ? productoMasVendido.getProducto()
                        : null,
                productoMasVendido != null
                        ? productoMasVendido.getCantidad()
                        : null,
                ingredientesStockBajo
        );
    }

}
