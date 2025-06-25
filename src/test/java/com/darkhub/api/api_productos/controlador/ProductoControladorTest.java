package com.darkhub.api.api_productos.controlador;

import com.darkhub.api.api_productos.modelo.Producto;
import com.darkhub.api.api_productos.servicio.ProductoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoControlador.class)
class ProductoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServicio productoServicio;

    @Test
    void debeRetornarPaginaDeProductos() throws Exception {
        // Datos simulados
        List<Producto> productos = List.of(
            new Producto(1L, "Laptop", "Desc", 1200.0),
            new Producto(2L, "Mouse", "Desc", 20.0)
        );
        Page<Producto> pagina = new PageImpl<>(productos, PageRequest.of(0, 2), 2);

        // Simular comportamiento del servicio
        when(productoServicio.listarPaginado(any(Pageable.class))).thenReturn(pagina);

        // Ejecutar la prueba
        mockMvc.perform(get("/api/productos/paginado?page=0&size=2"))
                .andExpect(status().isOk()) // Verifica código 200
                .andExpect(jsonPath("$.content").isArray()) // Verifica que content sea un array
                .andExpect(jsonPath("$.content.length()").value(2)); // Verifica que tenga 2 elementos
    }
}
