package com.darkhub.api.api_productos.repositorio;

import com.darkhub.api.api_productos.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un repositorio de datos
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
