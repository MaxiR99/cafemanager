package com.cafemanager.cafemanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "recetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;


    @Column(nullable = false)
    private BigDecimal cantidad;

}
