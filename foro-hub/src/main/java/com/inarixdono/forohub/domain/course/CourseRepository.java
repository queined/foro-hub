package com.inarixdono.forohub.domain.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    Page<Course> findByStatusTrue(Pageable pagination);
    Optional<Course> findByIdAndStatusTrue(Integer id);
}
