package com.wagdynavas.selfkeep.controller;

import com.wagdynavas.selfkeep.dto.UserRequest;
import com.wagdynavas.selfkeep.dto.UserResponse;
import com.wagdynavas.selfkeep.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController
{
    private final AuthService authService;
    private final AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        Authentication authentication = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password())
        );


    }
}
