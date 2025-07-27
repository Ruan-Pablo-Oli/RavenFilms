package com.io.github.ruan_pablo_oli.ravenFilms;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RavenTests {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnTopFilms(){
        FluxExchangeResult<String> result = webTestClient
                .get()
                .uri("/ravenFilms")
                .exchange()
                .returnResult(String.class);

        if(result.getStatus().isError()){
            System.out.println("Conexão não feita!");
            System.out.println(result.getStatus().value());

        }else{
            String body = result.getResponseBody().toString();
            System.out.println("Conexão feita!");
        }

    }

}
