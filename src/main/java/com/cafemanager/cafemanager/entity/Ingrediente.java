package com.cafemanager.cafemanager.entity;


import com.cafemanager.cafemanager.enums.UnidadMedida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredientes")
@Getter
@Setter
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private BigDecimal stockActual;

    private BigDecimal stockMinimo;

    private BigDecimal costoCompra;

    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;

    @Column(nullable = false)
    private Boolean activo = true;
}
