package com.inarixdono.forohub.domain.course;

public record CourseDTO(Integer id, String name, Category category) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
