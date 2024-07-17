package com.inarixdono.forohub.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record NewUserDTO(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank @JsonProperty("confirm_password") String confirmPassword) {

}
