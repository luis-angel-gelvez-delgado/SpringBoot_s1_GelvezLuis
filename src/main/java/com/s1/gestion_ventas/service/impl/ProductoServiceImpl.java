package com.s1.gestion_ventas.service.impl;

import com.s1.gestion_ventas.dto.request.ProductoRequestDTO;
import com.s1.gestion_ventas.dto.response.ProductoResponseDTO;
import com.s1.gestion_ventas.mapper.ProductoMapper;
import com.s1.gestion_ventas.model.Producto;
import com.s1.gestion_ventas.repository.ProductoRepository;
import com.s1.gestion_ventas.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO dto) {
        Producto p = productoMapper.DTOAEntidad(dto);
        Producto insertado = productoRepository.save(p);
        return productoMapper.entidadADTO(insertado);
    }

    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto a actualizar"));
        productoMapper.actualizarEntidadDesdeDTO(p, dto);
        Producto actualizado = productoRepository.save(p);
        return productoMapper.entidadADTO(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto a eliminar"));
        productoRepository.delete(p);
    }

    @Override
    public List<ProductoResponseDTO> buscarTodos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(productoMapper::entidadADTO).toList();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto"));
        return productoMapper.entidadADTO(p);
    }
}