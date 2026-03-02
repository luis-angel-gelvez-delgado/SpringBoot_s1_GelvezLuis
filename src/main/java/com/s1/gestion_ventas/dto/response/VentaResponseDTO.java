package com.s1.gestion_ventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion de una venta registrada en el sistema")
public record VentaResponseDTO(

        @Schema(description = "Identificador unico de la venta", example = "1")
        Long id,

        @Schema(description = "Fecha y hora en la que se realizo la venta", example = "2025-03-10T14:30:00")
        LocalDateTime fecha,

        @Schema(description = "Valor total de la venta", example = "3000.00")
        BigDecimal total,

        @Schema(description = "Lista de detalles asociados a la venta")
        List<DetalleVentaResponseDTO> detalles
) {}