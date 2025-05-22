package com.darkhub.api.api_productos.controlador;

import com.darkhub.api.api_productos.modelo.Producto;
import com.darkhub.api.api_productos.servicio.ProductoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Define la clase como un controlador REST (devuelve JSON)
@RequestMapping("/api/productos") // Ruta base para todos los endpoints del controlador
@CrossOrigin(origins = "*")  // Permite peticiones desde cualquier origen (útil para Angular)
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> listar() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}") 
    public Producto obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping // Maneja peticiones HTTP POST (crear un nuevo recurso)
    public Producto crear(@RequestBody Producto producto) {
        return servicio.crear(producto);
    }

    @PutMapping("/{id}") // Maneja peticiones HTTP PUT (actualizar un recurso existente)
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return servicio.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}