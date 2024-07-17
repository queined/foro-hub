package com.inarixdono.forohub.domain.topic;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IncomingTopicDTO(
        @NotBlank String subject,
        @NotBlank String content,
        @NotNull @JsonProperty("user_id") Integer userId,
        @NotNull @JsonProperty("course_id") Integer courseId) {
}
