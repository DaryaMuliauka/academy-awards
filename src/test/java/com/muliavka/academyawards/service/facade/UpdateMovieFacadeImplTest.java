package com.muliavka.academyawards.service.facade;

import com.muliavka.academyawards.dao.entity.projection.MovieOmdbProjection;
import com.muliavka.academyawards.data.MovieOmdbProjectionImpl;
import com.muliavka.academyawards.service.movie.MovieServiceImpl;
import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import com.muliavka.academyawards.service.omdb.OmdbServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateMovieFacadeImplTest {

    @Mock
    private MovieServiceImpl movieService;
    @Mock
    private OmdbServiceImpl omdbService;

    private UpdateMovieFacadeImpl facade;

    @Before
    public void init() {
        facade = new UpdateMovieFacadeImpl(movieService, omdbService);
    }

    @Test
    public void testUpdateMovieFromImdb() {
        MovieInfoForUpdateDto updateDto1 = new MovieInfoForUpdateDto();
        MovieInfoForUpdateDto updateDto2 = new MovieInfoForUpdateDto();
        List<MovieInfoForUpdateDto> infoForUpdateList = List.of(updateDto1, updateDto2);
//        when(movieAwardService.getMoviesShortInfoList(anyInt(), anyInt(), any(Sort.Direction.class), any())).thenReturn()
        when(movieService.getAllMovieIdAndTitle()).thenReturn(getMovieOmdbProjectionList());
        when(omdbService.getOmdbDataForUpdateMovie(any())).thenReturn(infoForUpdateList);
        doNothing().when(movieService).updateMovieData(updateDto1);
        doThrow(new RuntimeException()).when(movieService).updateMovieData(updateDto2);

        facade.updateMovieFromImdb();

        verify(movieService).getAllMovieIdAndTitle();
        verify(omdbService).getOmdbDataForUpdateMovie(any());
        verify(movieService, times(infoForUpdateList.size())).updateMovieData(any(MovieInfoForUpdateDto.class));
    }

    public static List<MovieOmdbProjection> getMovieOmdbProjectionList() {
        return List.of(
                new MovieOmdbProjectionImpl(1L, "title1"),
                new MovieOmdbProjectionImpl(2L, "title2"),
                new MovieOmdbProjectionImpl(3L, "title3")
        );
    }
}
