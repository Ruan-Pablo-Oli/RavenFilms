package com.io.github.ruan_pablo_oli.ravenFilms.model;

import java.time.LocalDate;
import java.util.UUID;

public class Film {

    private UUID id;
    private String title;
    private String overview;
    private LocalDate release_date;
    private Double vote_average;

    public Film(){

    }

    public Film(UUID id, String title, String overview, LocalDate release_date, Double vote_average) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setDate_release(LocalDate release_date) {
        this.release_date = release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }
}
