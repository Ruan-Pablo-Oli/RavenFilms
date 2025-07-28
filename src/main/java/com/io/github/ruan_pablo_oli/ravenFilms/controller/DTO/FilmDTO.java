package com.io.github.ruan_pablo_oli.ravenFilms.controller.DTO;

import java.time.LocalDate;

public record FilmDTO(String title, String overview, Double vote_average, LocalDate release_date) {
}
