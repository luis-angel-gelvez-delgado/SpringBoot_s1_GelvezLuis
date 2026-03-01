package com.s1.gestion_ventas.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * EL record, permite ser llenado por constructor,
 * se puede acceder a sus datos, directamente por el nombre de la clase.
 * Con esto evitamos los getter, setters y demas validaciones. Centrandonos
 * unicamente en transferencia y obtención de datos.
 * */

public record ProductoRequestDTO(
        @NotBlank(message = "el nombre no puede estar vacio")
        @Size(min = 2, max = 50, message = "el nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @NotBlank(message = "la descripcion no puede estar vacia")
        @Size(min = 2, max = 100, message = "la descripcion debe tener entre 2 y 100 caracteres")
        String descripcion,

        @NotNull(message = "el precio no puede ser nulo")
        BigDecimal precio,

        @NotNull(message = "el stock no puede ser nulo")
        @Min(value = 0, message = "el stock no puede ser negativo")
        Integer stock
) {}