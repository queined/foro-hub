package com.inarixdono.forohub.domain.course;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "course")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean status = true;

    public Course(IncomingCourseDTO course) {
        this.name = course.name();
        this.category = course.category();
    }

    public void disable() {
        this.status = false;
    }

    public void update(UpdateCourseDTO courseDTO) {
        if (courseDTO.name() != null) {
            this.name = courseDTO.name();
        }
        if (courseDTO.category() != null) {
            this.category = courseDTO.category();
        }
    }
}
