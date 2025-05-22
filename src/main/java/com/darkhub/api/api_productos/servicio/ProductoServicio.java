package com.darkhub.api.api_productos.servicio;

import com.darkhub.api.api_productos.modelo.Producto;
import com.darkhub.api.api_productos.repositorio.ProductoRepositorio;
import com.darkhub.api.api_productos.excepcion.RecursoNoEncontradoExcepcion;

import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marca la clase como un servicio que contiene lógica de negocio
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    // ✅ Único constructor → Spring lo inyecta automáticamente
    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Producto> listarTodos() {
        return repositorio.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Producto no encontrado con ID: " + id));
    }

    public Producto crear(Producto producto) {
        return repositorio.save(producto);
    }

    public Producto actualizar(Long id, Producto detalles) {
        Producto producto = obtenerPorId(id);
        producto.setNombre(detalles.getNombre());
        producto.setDescripcion(detalles.getDescripcion());
        producto.setPrecio(detalles.getPrecio());
        return repositorio.save(producto);
    }

    public void eliminar(Long id) {
        Producto producto = obtenerPorId(id);
        repositorio.delete(producto);
    }
}
