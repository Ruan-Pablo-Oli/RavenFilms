package com.io.github.ruan_pablo_oli.ravenFilms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.service.RavenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ravenFilms")
public class RavenController {

    private RavenService ravenService;

    public RavenController(RavenService ravenService){
        this.ravenService = ravenService;
    }


    @GetMapping
    public ResponseEntity<?> buscarFilmes() throws JsonProcessingException {

        List<FilmDTO> films = ravenService.consumirTMDB();
        return ResponseEntity.ok().body(films);

    }


}
