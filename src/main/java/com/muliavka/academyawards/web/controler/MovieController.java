package com.muliavka.academyawards.web.controler;

import com.muliavka.academyawards.dao.entity.projection.MovieShortViewProjection;
import com.muliavka.academyawards.service.facade.UpdateMovieFacade;
import com.muliavka.academyawards.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final UpdateMovieFacade movieFacade;

    @Autowired
    public MovieController(MovieService movieService, UpdateMovieFacade movieFacade) {
        this.movieService = movieService;
        this.movieFacade = movieFacade;
    }

    @GetMapping("/short-info-list")
    public Page<MovieShortViewProjection> getShortMovieInfoList(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection,
                                                                @RequestParam(name = "sortFields", defaultValue = "boxOffice") String[] sortFields) {
        return movieService.getMoviesShortInfoList(pageNumber, pageSize, sortDirection, sortFields);
    }

    @PostMapping("/update/from-omdb")
    public ResponseEntity updateMovieFromImdb() {
        movieFacade.updateMovieFromImdb();

        return ResponseEntity.ok().build();
    }
}
