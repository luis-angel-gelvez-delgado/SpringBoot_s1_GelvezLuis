package com.s1.gestion_ventas.mapper;

import com.s1.gestion_ventas.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_ventas.dto.response.VentaResponseDTO;
import com.s1.gestion_ventas.model.Venta;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class VentaMapper {

    public VentaResponseDTO entidadADTO(Venta venta, List<DetalleVentaResponseDTO> detalles) {
        if (venta == null) return null;
        return new VentaResponseDTO(
                venta.getId(),
                venta.getFecha(),
                venta.getTotal(),
                detalles
        );
    }
}


//
