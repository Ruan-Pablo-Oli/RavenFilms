package com.io.github.ruan_pablo_oli.ravenFilms.service;


import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;
import com.io.github.ruan_pablo_oli.ravenFilms.model.Film;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmService {

    List<Film> filmsEmMemoria = new ArrayList<>();
    List<Film> filmsFavoritos = new ArrayList<>();
    public void salvarFilmesEmMemoria(List<FilmDTO> filmsApi){
        this.filmsEmMemoria = filmsApi.stream().map(
                filmDTO -> {
                    Film film = new Film();
                    film.setId(UUID.randomUUID());
                    film.setTitle(filmDTO.title());
                    film.setOverview(filmDTO.overview());
                    film.setDate_release(filmDTO.release_date());
                    film.setVote_average(filmDTO.vote_average());
                    return film;
                }).collect(Collectors.toList());
    }

    public void salvarFilmesFavoritos(UUID id){
        Optional<Film> filmFavorito = filmsEmMemoria.stream().filter(film -> film.getId().equals(id)).findFirst();
        if(filmFavorito.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Não foi encontrado nenhum filme com esse id!");
        }
        filmsFavoritos.add(filmFavorito.get());
    }


    public List<Film> getFilmsEmMemoria(){
        return filmsEmMemoria;
    }

    public List<Film> getFilmsFavoritos(){
        return filmsFavoritos;
    }


}
