package com.muliavka.academyawards.service.movie.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with data for update Movie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieInfoForUpdateDto implements Serializable {

    @ApiModelProperty(name = "Movie id", required = true)
    private Long movieId;

    @ApiModelProperty(name = "Movie box office", required = true)
    private Long boxOffice;

    @ApiModelProperty(name = "Omdb rating", required = true)
    private Double imdbRating;
}
