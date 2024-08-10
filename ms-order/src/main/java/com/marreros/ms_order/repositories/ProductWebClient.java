package com.marreros.ms_order.repositories;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.marreros.ms_order.models.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductWebClient {

    private WebClient webClient;

    public ProductWebClient() {
        this.webClient = WebClient.builder().build();
    }

    public Product getProductById(Long id) {

        log.info("empezando a hacer la consulta al fallback");

        return webClient
            .get()
            .uri("http://localhost:8787/ms-product-fallback/product/{id}", id) // Ruta de la API con parámetro
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Product.class) // Convierte el cuerpo de la respuesta a Mono<String>
            .log()
            .block(); // Bloquea hasta recibir la respuesta (no reactivo)
    }

}
