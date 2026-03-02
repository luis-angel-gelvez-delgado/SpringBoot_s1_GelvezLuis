package com.s1.gestion_ventas.controller;

import com.s1.gestion_ventas.dto.request.VentaRequestDTO;
import com.s1.gestion_ventas.dto.response.VentaResponseDTO;
import com.s1.gestion_ventas.service.impl.VentaServiceImpl;

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
@RequestMapping("/api/venta")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "API para la gestion de ventas del sistema")
public class VentaController {

    private final VentaServiceImpl ventaService;


    @Operation(
            summary = "Crear venta",
            description = "Registra una nueva venta con sus detalles asociados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos enviados en la solicitud")
    })
    @PostMapping
    public ResponseEntity<VentaResponseDTO> guardar(
            @RequestBody VentaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.guardarVenta(dto));
    }


    @Operation(
            summary = "Actualizar venta",
            description = "Actualiza la informacion de una venta existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> actualizar(
            @RequestBody VentaRequestDTO dto,
            @Parameter(description = "ID de la venta a actualizar", example = "1")
            @PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(ventaService.actualizarVenta(dto, id));
    }


    @Operation(
            summary = "Listar ventas",
            description = "Obtiene la lista completa de ventas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ventas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarTodos() {

        return ResponseEntity
                .ok()
                .body(ventaService.buscarTodos());
    }


    @Operation(
            summary = "Buscar venta por ID",
            description = "Obtiene la informacion de una venta segun su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> buscarId(
            @Parameter(description = "ID de la venta", example = "1")
            @PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(ventaService.buscarPorId(id));
    }


    @Operation(
            summary = "Eliminar venta",
            description = "Elimina una venta del sistema segun su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venta eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la venta a eliminar", example = "1")
            @PathVariable Long id) {

        ventaService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}