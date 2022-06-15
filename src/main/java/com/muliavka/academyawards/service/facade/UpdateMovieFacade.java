package com.muliavka.academyawards.service.facade;

public interface UpdateMovieFacade {

    /**
     * sends a request to the OMDB API for all movies in the database,
     * then gets the information (box office receipts, Imdb rating) and updates it in the database
     */
    void updateMovieFromImdb();
}
