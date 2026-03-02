package com.s1.gestion_ventas.controller;

import com.s1.gestion_ventas.dto.request.ProductoRequestDTO;
import com.s1.gestion_ventas.dto.response.ProductoResponseDTO;
import com.s1.gestion_ventas.service.impl.ProductoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoServiceImpl productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@RequestBody ProductoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardarProducto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@RequestBody ProductoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok().body(productoService.actualizarProducto(dto, id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(productoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok().body(productoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}