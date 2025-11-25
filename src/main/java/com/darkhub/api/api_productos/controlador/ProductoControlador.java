package com.darkhub.api.api_productos.controlador;

import com.darkhub.api.api_productos.modelo.Producto;
import com.darkhub.api.api_productos.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista completa de todos los productos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    public List<Producto> listar() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto específico mediante su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public Producto obtener(
            @Parameter(description = "ID del producto a buscar", required = true)
            @PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo producto", description = "Crea un nuevo producto en el sistema")
    @ApiResponse(responseCode = "200", description = "Producto creado exitosamente")
    public Producto crear(@RequestBody Producto producto) {
        return servicio.crear(producto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public Producto actualizar(
            @Parameter(description = "ID del producto a actualizar", required = true)
            @PathVariable Long id,
            @RequestBody Producto producto) {
        return servicio.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public void eliminar(
            @Parameter(description = "ID del producto a eliminar", required = true)
            @PathVariable Long id) {
        servicio.eliminar(id);
    }

    @GetMapping("/paginado")
    @Operation(summary = "Listar productos paginados", description = "Obtiene una lista paginada de productos. Soporta ordenamiento y filtrado")
    @ApiResponse(responseCode = "200", description = "Página de productos obtenida exitosamente")
    public Page<Producto> obtenerPaginado(
            @Parameter(description = "Parámetros de paginación (page, size, sort)")
            Pageable pageable) {
        return servicio.listarPaginado(pageable);
    }
}