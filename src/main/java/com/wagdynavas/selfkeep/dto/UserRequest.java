package com.wagdynavas.selfkeep.dto;

public record UserRequest(String username, String password, String confirmPassword, String email) {

}
