package com.s1.gestion_ventas.controller;

import com.s1.gestion_ventas.dto.request.ProductoRequestDTO;
import com.s1.gestion_ventas.dto.response.ProductoResponseDTO;
import com.s1.gestion_ventas.service.impl.ProductoServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para la gestion de productos del sistema")
public class ProductoController {

    private final ProductoServiceImpl productoService;

    @Operation(
            summary = "Crear producto",
            description = "Registra un nuevo producto en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos enviados en la solicitud")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(
            @RequestBody ProductoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoService.guardarProducto(dto));
    }


    @Operation(
            summary = "Actualizar producto",
            description = "Actualiza la informacion de un producto existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @RequestBody ProductoRequestDTO dto,
            @Parameter(description = "ID del producto a actualizar", example = "1")
            @PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(productoService.actualizarProducto(dto, id));
    }


    @Operation(
            summary = "Listar productos",
            description = "Obtiene la lista completa de productos registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {

        return ResponseEntity
                .ok()
                .body(productoService.buscarTodos());
    }


    @Operation(
            summary = "Buscar producto por ID",
            description = "Obtiene la informacion de un producto especifico segun su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarId(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(productoService.buscarPorId(id));
    }


    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto del sistema según su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto a eliminar", example = "1")
            @PathVariable Long id) {

        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}