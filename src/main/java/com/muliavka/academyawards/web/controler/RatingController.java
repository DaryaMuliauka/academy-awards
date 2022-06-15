package com.muliavka.academyawards.web.controler;

import com.muliavka.academyawards.dao.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.service.rating.RatingService;
import com.muliavka.academyawards.web.api.rating.RatingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/rating")
public class RatingController {

    private final RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/by-title/{movieId}")
    public RatingViewProjection getRatingByUserIdAndTitleId(@PathVariable("userId") final Long userId,
                                                            @PathVariable("movieId") final Long movieId) {

        return service.getRatingByUserIdAndTitleId(userId, movieId);
    }

    @PostMapping("/save")
    public ResponseEntity saveRating(@RequestBody @NotNull RatingDto rating) {
        service.saveRating(rating);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity updateRating(@RequestBody @NotNull RatingDto rating) {
        service.updateRating(rating);

        return ResponseEntity.ok().build();
    }
}
