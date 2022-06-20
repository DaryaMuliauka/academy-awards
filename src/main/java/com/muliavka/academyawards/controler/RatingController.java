package com.muliavka.academyawards.controler;

import com.muliavka.academyawards.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.service.rating.RatingService;
import com.muliavka.academyawards.dto.rating.RatingDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/rating")
public class RatingController {

    private final RatingService service;

    @GetMapping("/{userId}/by-title/{movieId}")
    @ApiOperation(value = "Get rating by user ID and movie ID")
    public RatingViewProjection getRatingByUserIdAndTitleId(@PathVariable("userId") final Long userId,
                                                            @PathVariable("movieId") final Long movieId) {

        return service.getRatingByUserIdAndTitleId(userId, movieId);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save rating")
    public ResponseEntity saveRating(@RequestBody @NotNull RatingDto rating) {
        service.saveRating(rating);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @ApiOperation(value = "Update rating")
    public ResponseEntity updateRating(@RequestBody @NotNull RatingDto rating) {

        service.updateRating(rating);

        return ResponseEntity.ok().build();
    }
}
