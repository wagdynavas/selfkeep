package com.wagdynavas.selfkeep.com.wagdynavas.selfkeep.utils;

import com.wagdynavas.selfkeep.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JwtServiceTest {


    @Autowired
    JwtService jwtService;

    @Test
    void generateTokenTest() {

        String username = "Nameless King";
        String encode = new BCryptPasswordEncoder().encode("testpass");
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, encode);
        String token = jwtService.generateToken(authentication);

        String result = jwtService.extractUsername(token);

        assertEquals(username, result);
    }
}
