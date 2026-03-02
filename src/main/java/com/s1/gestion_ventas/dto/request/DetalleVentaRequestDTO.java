package com.s1.gestion_ventas.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO utilizado para registrar un detalle dentro de una venta")
public record DetalleVentaRequestDTO(

        @Schema(
                description = "ID del producto que se desea vender",
                example = "1"
        )
        @NotNull(message = "el producto no puede ser nulo")
        Long productoId,

        @Schema(
                description = "Cantidad de unidades del producto",
                example = "2"
        )
        @NotNull(message = "la cantidad no puede ser nula")
        @Min(value = 1, message = "la cantidad debe ser minimo 1")
        Integer cantidad

) {}