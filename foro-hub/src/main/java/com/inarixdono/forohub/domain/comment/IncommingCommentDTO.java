package com.inarixdono.forohub.domain.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IncommingCommentDTO(
        @NotBlank String content,
        @NotNull @JsonProperty("user_id") Integer userId,
        @NotNull @JsonProperty("topic_id") Integer topicId) {

}
