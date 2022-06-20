package com.muliavka.academyawards.entity;

import com.muliavka.academyawards.entity.enums.OskarCategory;
import com.muliavka.academyawards.entity.enums.Winner;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Data about movie award
 */
@Data
@Entity
public class Movie extends BaseEntity implements Serializable {

    @ApiModelProperty(name = "The year of oskar", required = true)
    private String oskarYear;

    @ApiModelProperty(name = "Oskar category", required = true)
    @Enumerated(EnumType.STRING)
    private OskarCategory category;

    @ApiModelProperty(name = "Nominee (movie title)", required = true)
    private String nominee;

    @ApiModelProperty(name = "Additional information about nominee", required = true)
    private String additionalInfo;

    @ApiModelProperty(name = "Win oskar or not", required = true)
    @Enumerated(EnumType.STRING)
    private Winner isWinner;

    @ApiModelProperty(name = "box office", required = true)
    private Long boxOffice;

    @ApiModelProperty(name = "Imdb rating", required = true)
    private Double imdbRating;

    @ApiModelProperty(name = "User's grade", required = true)
    @OneToMany(mappedBy = "id.movieId", fetch = FetchType.LAZY)
    private Set<Rating> grades;

}
