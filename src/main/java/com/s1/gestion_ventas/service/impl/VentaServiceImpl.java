package com.s1.gestion_ventas.service.impl;

import com.s1.gestion_ventas.dto.request.VentaRequestDTO;
import com.s1.gestion_ventas.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_ventas.dto.response.VentaResponseDTO;
import com.s1.gestion_ventas.mapper.DetalleVentaMapper;
import com.s1.gestion_ventas.mapper.ProductoMapper;
import com.s1.gestion_ventas.mapper.VentaMapper;
import com.s1.gestion_ventas.model.DetalleVenta;
import com.s1.gestion_ventas.model.Producto;
import com.s1.gestion_ventas.model.Venta;
import com.s1.gestion_ventas.repository.DetalleVentaRepository;
import com.s1.gestion_ventas.repository.ProductoRepository;
import com.s1.gestion_ventas.repository.VentaRepository;
import com.s1.gestion_ventas.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaMapper ventaMapper;
    private final ProductoMapper productoMapper;
    private final DetalleVentaMapper detalleVentaMapper;

    @Override
    public VentaResponseDTO guardarVenta(VentaRequestDTO dto) {
        // 1. Crear la venta vacía
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(BigDecimal.ZERO);
        Venta ventaGuardada = ventaRepository.save(venta);

        // 2. Procesar cada detalle
        BigDecimal total = BigDecimal.ZERO;
        List<DetalleVenta> detalles = new ArrayList<>();

        for (var detalleDTO : dto.detalles()) {
            Producto producto = productoRepository.findById(detalleDTO.productoId())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el producto"));
            DetalleVenta detalle = detalleVentaMapper.crearEntidad(
                    detalleDTO.cantidad(), producto, ventaGuardada
            );
            detalles.add(detalleVentaRepository.save(detalle));
            total = total.add(detalle.getSubtotal());
        }

        // 3. Actualizar el total
        ventaGuardada.setTotal(total);
        ventaRepository.save(ventaGuardada);

        // 4. Armar y retornar el response
        List<DetalleVentaResponseDTO> detallesDTO = detalles.stream()
                .map(detalleVentaMapper::entidadADTO)
                .toList();
        return ventaMapper.entidadADTO(ventaGuardada, detallesDTO);
    }

    @Override
    public VentaResponseDTO actualizarVenta(VentaRequestDTO dto, Long id) {
        return null;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la venta a eliminar"));
        ventaRepository.delete(v);
    }

    @Override
    public List<VentaResponseDTO> buscarTodos() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream().map(venta -> {
            List<DetalleVentaResponseDTO> detallesDTO = detalleVentaRepository
                    .findByVentaId(venta.getId())
                    .stream()
                    .map(detalleVentaMapper::entidadADTO)
                    .toList();
            return ventaMapper.entidadADTO(venta, detallesDTO);
        }).toList();
    }

    @Override
    public VentaResponseDTO buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la venta"));
        List<DetalleVentaResponseDTO> detallesDTO = detalleVentaRepository
                .findByVentaId(venta.getId())
                .stream()
                .map(detalleVentaMapper::entidadADTO)
                .toList();
        return ventaMapper.entidadADTO(venta, detallesDTO);
    }
}
