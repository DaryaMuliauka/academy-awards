package com.muliavka.academyawards.controler;

import com.muliavka.academyawards.security.AuthenticationService;
import com.muliavka.academyawards.security.jwt.JwtToken;
import com.muliavka.academyawards.dto.authentication.AuthenticationRequest;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "Authorizes the user")
    public JwtToken authenticate(@Valid @RequestBody AuthenticationRequest authRequest) {
        logger.debug("Call method authenticate, login = " + authRequest.getUserName());
        return authenticationService.authenticate(authRequest);
    }
}
