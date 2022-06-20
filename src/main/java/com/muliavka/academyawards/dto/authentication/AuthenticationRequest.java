package com.muliavka.academyawards.dto.authentication;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User's authentication request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @ApiModelProperty(name = "User name (login)", required = true)
    @NotNull(message = "user name can not be empty")
    @Size(max = 32, message = "user name must be less than 32 symbols")
    private String userName;

    @ApiModelProperty(name = "Password", required = true)
    @NotNull(message = "password can not be empty")
    @Size(max = 32, message = "user name must be less than 32 symbols")
    private String password;
}
