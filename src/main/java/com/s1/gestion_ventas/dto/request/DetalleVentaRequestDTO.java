package com.s1.gestion_ventas.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRequestDTO(
        @NotNull(message = "el producto no puede ser nulo")
        Long productoId,

        @NotNull(message = "la cantidad no puede ser nula")
        @Min(value = 1, message = "la cantidad debe ser minimo 1")
        Integer cantidad
) {}