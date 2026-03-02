package com.s1.gestion_ventas.dto.response;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion de un producto registrado en el sistema")
public record ProductoResponseDTO(

        @Schema(description = "Identificador unico del producto", example = "1")
        Long id,

        @Schema(description = "Nombre del producto", example = "Laptop Gamer")
        String nombre,

        @Schema(description = "Descripcion del producto", example = "Laptop gamer con 16GB RAM y SSD de 512GB")
        String descripcion,

        @Schema(description = "Precio del producto", example = "1500.00")
        BigDecimal precio,

        @Schema(description = "Cantidad disponible en inventario", example = "10")
        Integer stock
) {}