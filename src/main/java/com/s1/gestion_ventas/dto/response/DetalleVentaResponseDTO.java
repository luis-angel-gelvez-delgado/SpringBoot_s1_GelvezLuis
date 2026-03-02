package com.s1.gestion_ventas.dto.response;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa el detalle de una venta")
public record DetalleVentaResponseDTO(

        @Schema(description = "ID del detalle de venta", example = "1")
        Long id,

        @Schema(description = "Cantidad de productos vendidos", example = "2")
        Integer cantidad,

        @Schema(description = "Precio unitario del producto en el momento de la venta", example = "50000")
        BigDecimal precioUnitario,

        @Schema(description = "Subtotal calculado del detalle", example = "100000")
        BigDecimal subtotal,

        @Schema(description = "Producto asociado al detalle de venta")
        ProductoResponseDTO producto

) {}