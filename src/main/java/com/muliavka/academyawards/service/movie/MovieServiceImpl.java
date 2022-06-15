package com.muliavka.academyawards.service.movie;

import com.muliavka.academyawards.dao.entity.projection.MovieOmdbProjection;
import com.muliavka.academyawards.dao.entity.projection.MovieShortViewProjection;
import com.muliavka.academyawards.dao.repository.MovieRepository;
import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.muliavka.academyawards.util.LoggerUtil.logRequest;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MovieShortViewProjection> getMoviesShortInfoList(int pageNumber,
                                                                 int pageSize,
                                                                 Sort.Direction sortDirection,
                                                                 String[] sortFields) {
        final String methodName = "getMoviesShortInfoList";
        logRequest(logger, methodName, pageNumber, pageSize, sortDirection, Arrays.toString(sortFields));

        final Sort sort = Sort.by(sortDirection, sortFields);
        final PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,  sort);
        return movieRepository.getAllMoviesShortInfo(pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieOmdbProjection> getAllMovieIdAndTitle() {
        return movieRepository.getAllIdAndTitle();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateMovieData(MovieInfoForUpdateDto info) {
        final String methodName = "updateMovieAwardData";
        logRequest(logger, methodName, info);

        final Long boxOffice = info.getBoxOffice();
        final Double imdbRating = info.getImdbRating();
        final Long id = info.getMovieId();
        movieRepository.updateBoxOfficeAndOmdbRatingById(id, boxOffice, imdbRating);
    }
}
