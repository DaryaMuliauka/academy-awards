package com.muliavka.academyawards.service.facade;

import com.muliavka.academyawards.entity.projection.MovieOmdbProjection;
import com.muliavka.academyawards.service.movie.MovieService;
import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import com.muliavka.academyawards.service.omdb.OmdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.muliavka.academyawards.util.LoggerUtil.logException;
import static com.muliavka.academyawards.util.LoggerUtil.logRequest;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMovieFacadeImpl implements UpdateMovieFacade {

    private static final Logger logger = LoggerFactory.getLogger(UpdateMovieFacadeImpl.class);

    private final MovieService movieService;
    private final OmdbService omdbService;

    @Override
    public void updateMovieFromImdb() {
        final String methodName = "updateMovieFromImdb";
        logRequest(logger, methodName, "");

        final List<MovieOmdbProjection> movieProjectionList = movieService.getAllMovieIdAndTitle();
        if (isNotEmpty(movieProjectionList)) {
            final Map<Long, String> movieIdAndNomineeMap = getMovieIdAndTitleMap(movieProjectionList);
            final List<MovieInfoForUpdateDto> infoForUpdateList = omdbService.getOmdbDataForUpdateMovie(movieIdAndNomineeMap);
            if (isNotEmpty(infoForUpdateList)) {
                infoForUpdateList
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .forEach(infoForUpdate -> {
                            try {
                                movieService.updateMovieData(infoForUpdate);
                            } catch (Exception ignoring) {
                                logException(logger, methodName, ignoring);
                            }
                        });
            }
        }
    }

    private Map<Long, String> getMovieIdAndTitleMap(List<MovieOmdbProjection> movieAwardProjectionList) {
        return movieAwardProjectionList
                .parallelStream()
                .collect(Collectors.toMap(MovieOmdbProjection::getId, MovieOmdbProjection::getTitle));
    }
}
