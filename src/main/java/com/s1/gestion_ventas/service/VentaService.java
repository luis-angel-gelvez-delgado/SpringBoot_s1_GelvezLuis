package com.s1.gestion_ventas.service;

import com.s1.gestion_ventas.dto.request.VentaRequestDTO;
import com.s1.gestion_ventas.dto.response.VentaResponseDTO;
import java.util.List;

public interface VentaService {
    VentaResponseDTO guardarVenta(VentaRequestDTO dto);
    VentaResponseDTO actualizarVenta(VentaRequestDTO dto, Long id);
    void eliminarVenta(Long id);
    List<VentaResponseDTO> buscarTodos();
    VentaResponseDTO buscarPorId(Long id);
}