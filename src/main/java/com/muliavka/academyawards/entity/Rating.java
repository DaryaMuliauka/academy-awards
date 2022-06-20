package com.muliavka.academyawards.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Data about rating
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating implements Serializable {

    @ApiModelProperty(name = "Multiple id", required = true)
    @EmbeddedId
    private RatingId id;

    @ApiModelProperty(name = "User's rating", required = true)
    @Size(min = 1, max = 10, message = "grade must be from 0 to 10")
    private Integer grade;

}
