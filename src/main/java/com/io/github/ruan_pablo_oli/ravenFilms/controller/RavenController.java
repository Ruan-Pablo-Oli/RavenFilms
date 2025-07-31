package com.io.github.ruan_pablo_oli.ravenFilms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.service.RavenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("ravenFilms")
public class RavenController {

    private RavenService ravenService;

    public RavenController(RavenService ravenService){
        this.ravenService = ravenService;
    }


    @GetMapping
    public String buscarFilmes(Model model) throws JsonProcessingException {

        List<FilmDTO> films = ravenService.consumirTMDB();
        model.addAttribute("films",films);
        return "films";

    }

    @GetMapping("/films")
    public ResponseEntity<?> buscarFilmes() {
        List<FilmDTO> films = ravenService.consumirTMDB();
        return ResponseEntity.ok().body(films);

    }

    @GetMapping("/buscar")
    public String buscarFilmesTitulo(@RequestParam(name = "title",defaultValue = "") String title,Model model){
        List<FilmDTO> filmsSemFiltro = ravenService.consumirTMDB();
        List<FilmDTO> films = filmsSemFiltro.stream().filter(filmDTO -> filmDTO.title().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
        model.addAttribute("films",films);
        return "films";
    }



}
