package com.muliavka.academyawards.dto.rating;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Dto with rating data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto implements Serializable {

    @ApiModelProperty(name = "User's movie rating", required = true)
    private Integer grade;

    @ApiModelProperty(name = "UserEntity id", required = true)
    private Long userId;

    @ApiModelProperty(name = "Movie id", required = true)
    private Long titleId;
}
