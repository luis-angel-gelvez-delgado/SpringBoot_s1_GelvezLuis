package com.s1.gestion_ventas.dto.response;

import java.math.BigDecimal;

public record DetalleVentaResponseDTO(
        Long id,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal,
        ProductoResponseDTO producto
) {}