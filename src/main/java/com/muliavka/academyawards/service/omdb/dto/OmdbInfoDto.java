package com.muliavka.academyawards.service.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with data from OMDB Service
 */
@Data
public class OmdbInfoDto implements Serializable {

    @ApiModelProperty(name = "Movie title", required = true)
    @NotBlank
    @JsonProperty("Title")
    private String title;

    @ApiModelProperty(name = "Movie box office", required = true)
    @JsonProperty("BoxOffice")
    private String boxOffice;

    @ApiModelProperty(name = "Movie Imdb rating", required = true)
    @JsonProperty("imdbRating")
    private String imdbRating;
}
