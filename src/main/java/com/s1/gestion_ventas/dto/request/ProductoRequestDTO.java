package com.s1.gestion_ventas.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * El record permite crear objetos inmutables usados para transferencia de datos.
 * Se utiliza para recibir la informacion necesaria para crear o actualizar productos.
 */

@Schema(description = "Datos necesarios para crear o actualizar un producto")
public record ProductoRequestDTO(

        @Schema(
                description = "Nombre del producto",
                example = "Laptop Gamer"
        )
        @NotBlank(message = "el nombre no puede estar vacio")
        @Size(min = 2, max = 50, message = "el nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(
                description = "Descripcion del producto",
                example = "Laptop gamer con 16GB RAM y SSD de 512GB"
        )
        @NotBlank(message = "la descripcion no puede estar vacia")
        @Size(min = 2, max = 100, message = "la descripcion debe tener entre 2 y 100 caracteres")
        String descripcion,

        @Schema(
                description = "Precio del producto",
                example = "1500.00"
        )
        @NotNull(message = "el precio no puede ser nulo")
        BigDecimal precio,

        @Schema(
                description = "Cantidad disponible en inventario",
                example = "10"
        )
        @NotNull(message = "el stock no puede ser nulo")
        @Min(value = 0, message = "el stock no puede ser negativo")
        Integer stock
) {}