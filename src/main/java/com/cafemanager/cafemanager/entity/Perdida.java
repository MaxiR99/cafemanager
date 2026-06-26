package com.cafemanager.cafemanager.entity;

import com.cafemanager.cafemanager.enums.MotivoPerdida;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perdidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perdida {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;


    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidad;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MotivoPerdida motivo;


    @Column(nullable = false)
    private LocalDateTime fecha;


    @Column(length = 500)
    private String observacion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}