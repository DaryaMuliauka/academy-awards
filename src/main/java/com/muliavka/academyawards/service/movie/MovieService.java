package com.muliavka.academyawards.service.movie;

import com.muliavka.academyawards.entity.Movie;
import com.muliavka.academyawards.entity.projection.MovieOmdbProjection;
import com.muliavka.academyawards.entity.projection.MovieShortViewProjection;
import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MovieService {

    /**
     * Returns information about Movie by page.
     * It's possible to pass sorting direction parameters, as well as an array with the names of the fields
     * by which sorting will be performed
     *
     * @param pageNumber number of page
     * @param pageSize number of element's per page
     * @param sortDirection DESK, ASK
     * @param sortFields can be any fields from Movie
     * @return View with short, main information about Movie
     *
     * @see Movie
     * @see MovieShortViewProjection
     */
    Page<MovieShortViewProjection> getMoviesShortInfoList(int pageNumber,
                                                          int pageSize,
                                                          Sort.Direction sortDirection,
                                                          String[] sortFields);

    /**
     * Returns fields id and nominee from all Movie entity
     *
     * @return MovieOmdbProjection
     *
     * @see MovieOmdbProjection
     * @see Movie
     */
    List<MovieOmdbProjection> getAllMovieIdAndTitle();

    /**
     * Update MovieAward entity by information in MovieInfoForUpdateDto
     *
     * @param info data for update
     *
     * @see MovieInfoForUpdateDto
     * @see Movie
     */
    void updateMovieData(MovieInfoForUpdateDto info);
}
