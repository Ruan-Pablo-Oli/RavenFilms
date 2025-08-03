package com.io.github.ruan_pablo_oli.ravenFilms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.model.Film;
import com.io.github.ruan_pablo_oli.ravenFilms.service.FilmService;
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
    private FilmService filmService;
    public RavenController(RavenService ravenService, FilmService filmService){
        this.ravenService = ravenService;
        this.filmService = filmService;
    }


    @GetMapping
    public String buscarFilmes(Model model) throws JsonProcessingException {
        List<Film> films =filmService.getFilmsEmMemoria();
        if(films.isEmpty()){
            List<FilmDTO> filmsDTO = ravenService.consumirTMDB();
            filmService.salvarFilmesEmMemoria(filmsDTO);
        }
        films = filmService.getFilmsEmMemoria();

        model.addAttribute("films",films);
        return "films";

    }

    @GetMapping("/films")
    public ResponseEntity<?> buscarFilmes() {
        List<Film> films =filmService.getFilmsEmMemoria();
        if(films.isEmpty()){
            List<FilmDTO> filmsDTO = ravenService.consumirTMDB();
            filmService.salvarFilmesEmMemoria(filmsDTO);
        }
        films = filmService.getFilmsEmMemoria();

        return ResponseEntity.ok().body(films);

    }

    @GetMapping("/buscar")
    public String buscarFilmesTitulo(@RequestParam(name = "title",defaultValue = "") String title,Model model){
        List<Film> filmsSemFiltro =filmService.getFilmsEmMemoria();
        if(filmsSemFiltro.isEmpty()){
            List<FilmDTO> filmsDTO = ravenService.consumirTMDB();
            filmService.salvarFilmesEmMemoria(filmsDTO);
        }
        filmsSemFiltro = filmService.getFilmsEmMemoria();

        List<Film> films = filmsSemFiltro.stream().filter(film -> film.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
        model.addAttribute("films",films);
        return "films";
    }



}
