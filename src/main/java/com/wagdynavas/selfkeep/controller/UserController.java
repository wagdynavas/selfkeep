package com.wagdynavas.selfkeep.controller;

import com.wagdynavas.selfkeep.dto.UserRequest;
import com.wagdynavas.selfkeep.dto.UserResponse;
import com.wagdynavas.selfkeep.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.wagdynavas.selfkeep.UserErrors.EMAIL_NOT_FOUND;
import static com.wagdynavas.selfkeep.UserErrors.UNMATCHED_USER_PASSWORD;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        ResponseEntity<UserResponse> responseEntity = userService.validateUserRequest(userRequest);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        }

        return userService.createUser(userRequest);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam("email") String email) {

        Optional<UserResponse> userResponseOptional = userService.getUserByEmail(email);

        return userResponseOptional
                .map(userResponse -> ResponseEntity.ok().body(userResponse))
                .orElseGet(() -> ResponseEntity.badRequest()
                .body(UserResponse.builder()
                        .error(EMAIL_NOT_FOUND.getErrorCode())
                        .message(EMAIL_NOT_FOUND.getErrorMessage())
                        .email(email)
                        .build()));

    }

    /*
     * TODO ALl the other functions will require Oauth authorization
     */
}
