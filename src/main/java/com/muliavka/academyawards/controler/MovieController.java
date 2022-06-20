package com.muliavka.academyawards.controler;

import com.muliavka.academyawards.entity.projection.MovieShortViewProjection;
import com.muliavka.academyawards.service.facade.UpdateMovieFacade;
import com.muliavka.academyawards.service.movie.MovieService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final UpdateMovieFacade movieFacade;

    @GetMapping("/short-info-list")
    @ApiOperation(value = "Get short information about Movie")
    public Page<MovieShortViewProjection> getShortMovieInfoList(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection,
                                                                @RequestParam(name = "sortFields", defaultValue = "boxOffice") String[] sortFields) {
        return movieService.getMoviesShortInfoList(pageNumber, pageSize, sortDirection, sortFields);
    }

    @PostMapping("/update/from-omdb")
    @ApiOperation(value = "Update information about Movie")
    public ResponseEntity updateMovieFromImdb() {
        movieFacade.updateMovieFromImdb();

        return ResponseEntity.ok().build();
    }
}
