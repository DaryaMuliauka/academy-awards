package com.muliavka.academyawards.web.controler;

import com.muliavka.academyawards.dao.entity.projection.MovieShortViewProjection;
import com.muliavka.academyawards.service.facade.UpdateMovieFacadeImpl;
import com.muliavka.academyawards.service.movie.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    private MovieServiceImpl movieService;

    @Mock
    private UpdateMovieFacadeImpl movieFacade;

    private MovieController movieController;

    @Before
    public void init() {
        movieController = new MovieController(movieService, movieFacade);
    }

    @Test
    public void testGetShortMovieInfoList() {
        int pageNumber = 1, pageSize = 1;
        String [] sortedFields = new String[] {"boxOffice"};
        when(movieService.getMoviesShortInfoList(pageNumber, pageSize, Sort.Direction.DESC, sortedFields)).thenReturn(new PageImpl<>(new ArrayList<>()));

        Page<MovieShortViewProjection> page = movieController.getShortMovieInfoList(pageNumber, pageSize, Sort.Direction.DESC, sortedFields);

        verify(movieService).getMoviesShortInfoList(pageNumber, pageSize, Sort.Direction.DESC, sortedFields);
        assertNotNull(page);
    }

    @Test
    public void testUpdateMovieFromImdb() {
        doNothing().when(movieFacade).updateMovieFromImdb();

        ResponseEntity response = movieController.updateMovieFromImdb();

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
