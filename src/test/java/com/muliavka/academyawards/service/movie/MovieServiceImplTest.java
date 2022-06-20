package com.muliavka.academyawards.service.movie;

import com.muliavka.academyawards.entity.projection.MovieShortViewProjection;
import com.muliavka.academyawards.repository.MovieRepository;
import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    private MovieServiceImpl movieService;

    @Before
    public void init() {
        movieService = new MovieServiceImpl(movieRepository);
    }

    @Test
    public void testGetMoviesShortInfoList() {
        when(movieRepository.getAllMoviesShortInfo(any(PageRequest.class))).thenReturn(new PageImpl(new ArrayList()));

        Page<MovieShortViewProjection> resultPage = movieService.getMoviesShortInfoList(1, 1, Sort.Direction.DESC, new String[] {"1234"});

        verify(movieRepository).getAllMoviesShortInfo(any(PageRequest.class));

        assertNotNull(resultPage);
    }

    @Test
    public void testGetAllMovieIdAndTitle() {
        when(movieRepository.getAllIdAndTitle()).thenReturn(null);

        movieService.getAllMovieIdAndTitle();

        verify(movieRepository).getAllIdAndTitle();
    }

    @Test
    public void testUpdateMovieData() {
        MovieInfoForUpdateDto dto = new MovieInfoForUpdateDto();
        dto.setMovieId(1L);
        dto.setImdbRating(2.0);
        dto.setBoxOffice(10L);
        doNothing().when(movieRepository).updateBoxOfficeAndOmdbRatingById(1L, 10L, 2.0);

        movieService.updateMovieData(dto);

        verify(movieRepository).updateBoxOfficeAndOmdbRatingById(1L, 10L, 2.0);
    }
}
