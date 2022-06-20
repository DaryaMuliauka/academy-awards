package com.muliavka.academyawards.service.omdb.mapper;

import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import com.muliavka.academyawards.service.omdb.dto.OmdbInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.muliavka.academyawards.util.LoggerUtil.*;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Service
@RequiredArgsConstructor
@Slf4j
public class OmdbDataToDataBaseDataMapperImpl implements OmdbDataToDataBaseDataMapper {

    private static final Logger logger = LoggerFactory.getLogger(OmdbDataToDataBaseDataMapperImpl.class);

    private static final String UNKNOWN_OMDB_VALUE = "N/A";
    private static final String REG_EX = "[$ .,]";

    @Override
    public MovieInfoForUpdateDto map(Long movieId, OmdbInfoDto omdbInfo) {
        final String methodName = "map";
        logRequest(logger, methodName, movieId, omdbInfo);

        final MovieInfoForUpdateDto infoForUpdate = new MovieInfoForUpdateDto();
        infoForUpdate.setMovieId(movieId);
        setImdbRating(infoForUpdate, omdbInfo);
        setBoxOffice(infoForUpdate, omdbInfo);

        logResponse(logger, methodName, omdbInfo);
        return infoForUpdate;
    }

    private void setImdbRating(MovieInfoForUpdateDto infoForUpdate, OmdbInfoDto omdbInfo) {
        final String methodName = "setImdbRating";
        logRequest(logger, methodName, infoForUpdate, omdbInfo);

        final String imdbRating = omdbInfo.getImdbRating();
        if (isNotBlank(imdbRating) && !UNKNOWN_OMDB_VALUE.equals(imdbRating)) {
            try {
                Double movieImdbRating = Double.valueOf(imdbRating);
                infoForUpdate.setImdbRating(movieImdbRating);
            } catch (NumberFormatException e) {
                logException(logger, methodName, e);
            }
        }
    }

    private void setBoxOffice(MovieInfoForUpdateDto infoForUpdate, OmdbInfoDto omdbInfo) {
        final String methodName = "setBoxOffice";
        logRequest(logger, methodName, infoForUpdate, omdbInfo);

        final String boxOffice = omdbInfo.getBoxOffice();
        if (isNotBlank(boxOffice) && !UNKNOWN_OMDB_VALUE.equals(boxOffice)) {
            try {
                Long movieBoxOffice = Long.valueOf(boxOffice.replaceAll(REG_EX, ""));
                infoForUpdate.setBoxOffice(movieBoxOffice);
            } catch (NumberFormatException e) {
                logException(logger, methodName, e);
            }
        }
    }
}
