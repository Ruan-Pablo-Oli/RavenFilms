package com.io.github.ruan_pablo_oli.ravenFilms.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RavenService {

    @Value("${ravenApi.key}")
    private String apiKey;

    @Value("${ravenApi.url}")
    private String apiUrl;

    private final WebClient webClient = WebClient.create();

    public String consumirTMDB(){
        System.out.println(apiKey);
        System.out.println(apiUrl);

        String urlComToken = apiUrl + "?api_key=" + apiKey;
        return webClient.get()
                .uri(urlComToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }



}
