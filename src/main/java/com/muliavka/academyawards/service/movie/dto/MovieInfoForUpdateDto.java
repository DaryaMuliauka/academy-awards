package com.muliavka.academyawards.service.movie.dto;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with data for update MovieEntity
 */
public class MovieInfoForUpdateDto implements Serializable {

    /**
     * MovieEntity id
     */
    private Long movieId;

    /**
     * Movie box office
     */
    private Long boxOffice;

    /**
     * Omdb rating
     */
    private Double imdbRating;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", MovieInfoForUpdateDto.class.getName() + "{", "}")
                .add("movieId = '" + movieId + "'")
                .add("boxOffice = '" + boxOffice + "'")
                .add("imdbRating = '" + imdbRating + "'")
                .toString();
    }
}
