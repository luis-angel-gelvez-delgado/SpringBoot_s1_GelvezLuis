package com.s1.gestion_ventas.service;

import com.s1.gestion_ventas.dto.request.ProductoRequestDTO;
import com.s1.gestion_ventas.dto.response.ProductoResponseDTO;
import java.util.List;

public interface ProductoService {
    ProductoResponseDTO guardarProducto(ProductoRequestDTO dto);
    ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id);
    void eliminarProducto(Long id);
    List<ProductoResponseDTO> buscarTodos();
    ProductoResponseDTO buscarPorId(Long id);
}