package com.io.github.ruan_pablo_oli.ravenFilms.service;


import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.model.Film;
import com.io.github.ruan_pablo_oli.ravenFilms.model.TMDBResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class RavenService {

    @Value("${ravenApi.key}")
    private String apiKey;

    @Value("${ravenApi.url}")
    private String apiUrl;

    private final WebClient webClient = WebClient.create();

    public List<FilmDTO> consumirTMDB(){
        String urlComToken = apiUrl + "?api_key=" + apiKey + "&page=1";
        TMDBResponse response =  webClient.get()
                .uri(urlComToken)
                .retrieve()
                .bodyToMono(TMDBResponse.class)
                .block();

        return response != null ? response.getResults()  : Collections.emptyList();
    }



}
