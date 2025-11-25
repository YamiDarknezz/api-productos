package com.darkhub.api.api_productos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:5051}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:" + serverPort);
        localServer.setDescription("Servidor Local");

        Contact contact = new Contact();
        contact.setName("DarkHub API");
        contact.setUrl("https://github.com/yamidarknezz");

        Info info = new Info()
                .title("API de Gestión de Productos")
                .version("1.0.0")
                .description("API REST para la gestión de productos. " +
                        "Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre productos. " +
                        "Soporta paginación para consultas eficientes.")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
