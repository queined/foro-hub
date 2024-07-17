package com.inarixdono.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(@NotNull Integer id, @NotBlank String subject, @NotBlank String content) {

}
