package com.io.github.ruan_pablo_oli.ravenFilms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.io.github.ruan_pablo_oli.ravenFilms.service.RavenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ravenFilms")
public class RavenController {

    private RavenService ravenService;

    public RavenController(RavenService ravenService){
        this.ravenService = ravenService;
    }


    @GetMapping
    public ResponseEntity<?> buscarFilmes() throws JsonProcessingException {

        String json = ravenService.consumirTMDB();
        ObjectMapper mapper = new ObjectMapper();
        Object jsonObj = mapper.readValue(json,Object.class);
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

        return ResponseEntity.ok().body(writer.writeValueAsString(jsonObj));

    }


}
