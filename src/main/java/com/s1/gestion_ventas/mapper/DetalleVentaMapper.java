package com.s1.gestion_ventas.mapper;

import com.s1.gestion_ventas.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_ventas.model.DetalleVenta;
import com.s1.gestion_ventas.model.Producto;
import com.s1.gestion_ventas.model.Venta;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DetalleVentaMapper {

    private final ProductoMapper productoMapper;

    public DetalleVentaMapper(ProductoMapper productoMapper) {
        this.productoMapper = productoMapper;
    }

    public DetalleVentaResponseDTO entidadADTO(DetalleVenta detalle) {
        if (detalle == null) return null;
        return new DetalleVentaResponseDTO(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal(),
                productoMapper.entidadADTO(detalle.getProducto())
        );
    }

    public DetalleVenta crearEntidad(Integer cantidad, Producto producto, Venta venta) {
        if (cantidad == null || producto == null || venta == null) return null;
        DetalleVenta d = new DetalleVenta();
        d.setCantidad(cantidad);
        d.setPrecioUnitario(producto.getPrecio());
        d.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        d.setProducto(producto);
        d.setVenta(venta);
        return d;
    }
}