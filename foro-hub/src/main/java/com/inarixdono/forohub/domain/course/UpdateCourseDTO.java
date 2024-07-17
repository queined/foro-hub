package com.inarixdono.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCourseDTO(
        @NotNull Integer id,
        @NotBlank String name,
        @NotNull Category category) {

}
