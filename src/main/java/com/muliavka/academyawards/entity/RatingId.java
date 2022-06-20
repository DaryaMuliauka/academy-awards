package com.muliavka.academyawards.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RatingId implements Serializable {

    @ManyToOne
    @ApiModelProperty(name = "Movie id", required = true)
    private Movie movieId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(name = "user id", required = true)
    private UserEntity userId;

}
