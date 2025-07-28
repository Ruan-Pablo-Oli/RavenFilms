package com.io.github.ruan_pablo_oli.ravenFilms.model;


import java.time.LocalDate;

public class Film {

    private String title;
    private String URL;
    private Double vote_average;
    private LocalDate release_data;

    public Film(String title, String URL, Double vote_average, LocalDate release_data) {
        this.title = title;
        this.URL = URL;
        this.vote_average = vote_average;
        this.release_data = release_data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public LocalDate getRelease_data() {
        return release_data;
    }

    public void setRelease_data(LocalDate release_data) {
        this.release_data = release_data;
    }


}
