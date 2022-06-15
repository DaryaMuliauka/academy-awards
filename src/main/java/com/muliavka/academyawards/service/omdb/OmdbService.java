package com.muliavka.academyawards.service.omdb;

import com.muliavka.academyawards.dao.entity.MovieEntity;
import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;

import java.util.List;
import java.util.Map;

public interface OmdbService {

    /**
     * Sends async requests to OMDB API, gets information about movie by title and return data
     * for update MovieAward entity like box office and Imdb rating
     *
     * @param movieIdNomineeMap map with MovieAward entity id and movie title
     * @return data for update MovieAward entity with id and fields for update
     *
     * @see MovieInfoForUpdateDto
     * @see MovieEntity
     */
    List<MovieInfoForUpdateDto> getOmdbDataForUpdateMovie(Map<Long, String> movieIdNomineeMap);
}
