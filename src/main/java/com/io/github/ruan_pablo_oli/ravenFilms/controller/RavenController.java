package com.io.github.ruan_pablo_oli.ravenFilms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.service.RavenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("ravenFilms")
public class RavenController {

    private RavenService ravenService;

    public RavenController(RavenService ravenService){
        this.ravenService = ravenService;
    }


    @GetMapping("/films")
    public String buscarFilmes(Model model) throws JsonProcessingException {

        List<FilmDTO> films = ravenService.consumirTMDB();
        model.addAttribute("films",films);
        return "films";

    }


}
