package com.s1.gestion_ventas.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record VentaRequestDTO(
        @NotNull(message = "los detalles no pueden ser nulos")
        @NotEmpty(message = "la venta debe tener al menos un detalle")
        List<DetalleVentaRequestDTO> detalles
) {}