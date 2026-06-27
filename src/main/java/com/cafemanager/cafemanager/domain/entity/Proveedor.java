package com.cafemanager.cafemanager.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String telefono;

    @Column(unique = true)
    private String email;

    private String direccion;

    @Column(nullable = false)
    private Boolean activo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor")
    private List<Compra> compras = new ArrayList<>();
}