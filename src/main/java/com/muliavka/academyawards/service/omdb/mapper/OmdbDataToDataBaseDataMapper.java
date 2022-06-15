package com.muliavka.academyawards.service.omdb.mapper;

import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import com.muliavka.academyawards.service.omdb.dto.OmdbInfoDto;

public interface OmdbDataToDataBaseDataMapper {

    /**
     * maps data from OMDB API to data base format for update
     *
     * @param movieId MovieAward entity id
     * @param omdbInfo data from OMDB
     * @return dto with data for update
     *
     * @see MovieInfoForUpdateDto
     * @see OmdbInfoDto
     */
    MovieInfoForUpdateDto map(Long movieId, OmdbInfoDto omdbInfo);
}
