package com.s1.gestion_ventas.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos necesarios para registrar una venta")
public record VentaRequestDTO(

        @Schema(
                description = "Lista de productos incluidos en la venta",
                example = "[{ \"productoId\": 1, \"cantidad\": 2 }]"
        )
        @NotNull(message = "los detalles no pueden ser nulos")
        @NotEmpty(message = "la venta debe tener al menos un detalle")
        List<DetalleVentaRequestDTO> detalles
) {}