package com.darkhub.api.api_productos.config;

import com.darkhub.api.api_productos.modelo.Producto;
import com.darkhub.api.api_productos.repositorio.ProductoRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Marca una clase como componente gestionado por Spring
public class DatosIniciales {

    private final ProductoRepositorio productoRepositorio;

    public DatosIniciales(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @PostConstruct  // Ejecuta el método después de que Spring haya creado los beans
    public void init() {
        if (productoRepositorio.count() == 0) {
            // No hay productos, insertamos algunos de ejemplo
            List<Producto> productos = List.of(
                new Producto(null, "Laptop", "Laptop para desarrollo", 1500.00),
                new Producto(null, "Mouse", "Mouse inalámbrico", 25.50),
                new Producto(null, "Teclado", "Teclado mecánico", 75.00)
            );
            productoRepositorio.saveAll(productos);
            System.out.println("Datos iniciales insertados en la base de datos.");
        } else {
            System.out.println("La base de datos ya contiene productos, no se insertaron datos iniciales.");
        }
    }
}