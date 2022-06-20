package com.muliavka.academyawards.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Data about user
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class UserEntity extends BaseEntity implements Serializable {

    @ApiModelProperty(name = "User's name", required = true)
    @Column(length = 32, nullable = false)
    private String userName;

    @ApiModelProperty(name = "Encrypted password", required = true)
    @Column(length = 128, nullable = false)
    private String password;

    @OneToMany(mappedBy = "id.userId", fetch = FetchType.LAZY)
    private Set<Rating> grades;

}
