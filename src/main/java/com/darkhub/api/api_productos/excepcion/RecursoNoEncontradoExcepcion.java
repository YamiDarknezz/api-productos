package com.darkhub.api.api_productos.excepcion;

public class RecursoNoEncontradoExcepcion extends RuntimeException {
    public RecursoNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
}