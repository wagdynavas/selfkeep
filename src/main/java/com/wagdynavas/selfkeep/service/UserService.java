package com.wagdynavas.selfkeep.service;

import com.wagdynavas.selfkeep.dto.UserRequest;
import com.wagdynavas.selfkeep.dto.UserResponse;
import com.wagdynavas.selfkeep.model.User;
import com.wagdynavas.selfkeep.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.wagdynavas.selfkeep.UserErrors.NOT_UNIQUE_USER_EMAIL;
import static com.wagdynavas.selfkeep.UserErrors.UNMATCHED_USER_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEmail(userRequest.email());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserResponse> validateUserRequest(UserRequest userRequest) {

        if (!userRequest.password().equals(userRequest.confirmPassword())) {

            UserResponse userResponse = UserResponse.builder()
                    .username(userRequest.username())
                    .email(userRequest.email())
                    .error(UNMATCHED_USER_PASSWORD.getErrorCode())
                    .message(UNMATCHED_USER_PASSWORD.getErrorMessage())
                    .build();

            return ResponseEntity.badRequest().body(userResponse);
        }

        Optional<User> userOptional = userRepository.findByEmailOrUsername(userRequest.email(), userRequest.username());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .username(userRequest.username())
                            .email(userRequest.email())
                            .error(NOT_UNIQUE_USER_EMAIL.getErrorCode())
                            .message(NOT_UNIQUE_USER_EMAIL.getErrorMessage())
                            .build());
        }

        return ResponseEntity.ok().build();
    }

    public Optional<UserResponse> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::createUserResponse);


    }

    private UserResponse createUserResponse(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
