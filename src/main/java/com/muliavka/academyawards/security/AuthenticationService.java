package com.muliavka.academyawards.security;

import com.muliavka.academyawards.security.jwt.JwtToken;
import com.muliavka.academyawards.security.jwt.JwtTokenProvider;
import com.muliavka.academyawards.web.api.authentication.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public JwtToken authenticate(AuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtToken(tokenProvider.createToken(authentication));
    }
}
