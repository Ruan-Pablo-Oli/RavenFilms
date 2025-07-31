package com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO;

import java.time.LocalDate;

public record FilmDTO(String title, String overview, Double vote_average, LocalDate release_date) {
    public FilmDTO(String title, String overview, Double vote_average, LocalDate release_date) {
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }



    @Override
    public String title() {
        return title;
    }

    @Override
    public String overview() {
        return overview;
    }
}
