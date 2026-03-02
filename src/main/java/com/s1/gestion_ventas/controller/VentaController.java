package com.s1.gestion_ventas.controller;

import com.s1.gestion_ventas.dto.request.VentaRequestDTO;
import com.s1.gestion_ventas.dto.response.VentaResponseDTO;
import com.s1.gestion_ventas.service.impl.VentaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaServiceImpl ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> guardar(@RequestBody VentaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardarVenta(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> actualizar(@RequestBody VentaRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok().body(ventaService.actualizarVenta(dto, id));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(ventaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok().body(ventaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}