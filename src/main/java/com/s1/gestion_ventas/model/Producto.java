package com.s1.gestion_ventas.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * PRIMERO:
 * Se necesita Mappear la base de datos, para que, JPA identifique cuales son
 * las entidades que va a manipular.
 */


@Entity
@Table(name = "producto")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private BigDecimal precio;
    @Column(nullable = false)
    private Integer stock;
}

