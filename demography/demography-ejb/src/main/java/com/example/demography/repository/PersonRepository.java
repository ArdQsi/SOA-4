package com.example.demography.repository;

import com.example.demography.execption.ResourceNotFoundException;
import com.example.demography.model.Color;
import com.example.demography.model.Person;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.inject.Inject;
import javax.net.ssl.SSLException;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Stateless
public class PersonRepository {
    SslContext context = SslContextBuilder.forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build();

    HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

    private final WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl("https://localhost:8082").build();

    public PersonRepository() throws SSLException {
    }

    public Mono<List<Person>> getByAll() {
        return webClient.get().uri("/persons/filter").retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("Not found item"));
                } )
                .bodyToFlux(Person.class).collectList();
    }

    public Mono<List<Person>> getByEyeColor(Color eyeColor) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/persons/filter")
                .queryParam("eye-color", eyeColor)
                .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("Not found item"));
                } )
                .bodyToFlux(Person.class).collectList();
    }
}
