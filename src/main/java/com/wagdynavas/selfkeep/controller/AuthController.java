package com.wagdynavas.selfkeep.controller;

import com.wagdynavas.selfkeep.dto.UserRequest;
import com.wagdynavas.selfkeep.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController
{
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        Authentication authentication = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password())
        );

        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok().body(token);
    }
}
