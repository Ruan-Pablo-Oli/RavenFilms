package com.io.github.ruan_pablo_oli.ravenFilms.model;

import com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO.FilmDTO;

import java.util.List;

public class TMDBResponse {

    private List<FilmDTO> results;

    public List<FilmDTO> getResults() {
        return results;
    }

    public void setResults(List<FilmDTO> results) {
        this.results = results;
    }
}
