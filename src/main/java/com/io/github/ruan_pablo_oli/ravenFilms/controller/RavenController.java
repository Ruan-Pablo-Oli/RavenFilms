package com.io.github.ruan_pablo_oli.ravenFilms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.model.Film;
import com.io.github.ruan_pablo_oli.ravenFilms.service.FilmService;
import com.io.github.ruan_pablo_oli.ravenFilms.service.RavenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @GetMapping("/favoritos")
    public String buscarFilmesFavoritos(Model model){
        List<Film> films = filmService.getFilmsFavoritos();

        model.addAttribute("films",films);
        return "films";
    }

    @PostMapping("/favorito/{id}")
    public ResponseEntity<?> salvarFilmeFavorito(@PathVariable String id){
        if(filmService.getFilmsEmMemoria().isEmpty()){
            List<FilmDTO> filmDTOS = ravenService.consumirTMDB();
            filmService.salvarFilmesEmMemoria(filmDTOS);
        }
        filmService.salvarFilmesFavoritos(UUID.fromString(id));
        return ResponseEntity.accepted().body(Map.of(id,"Filme salvo!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilmes(@PathVariable String id){
        filmService.deletarFilme(UUID.fromString(id));
        return ResponseEntity.accepted().build();
    }



}
