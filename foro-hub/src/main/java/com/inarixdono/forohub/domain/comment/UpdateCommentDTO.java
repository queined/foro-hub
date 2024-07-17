package com.inarixdono.forohub.domain.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCommentDTO(
        @NotNull Integer id,
        @NotBlank String content) {
}
