package com.s1.gestion_ventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDTO(
        Long id,
        LocalDateTime fecha,
        BigDecimal total,
        List<DetalleVentaResponseDTO> detalles
) {}