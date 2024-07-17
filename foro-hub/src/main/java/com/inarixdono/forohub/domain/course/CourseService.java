package com.inarixdono.forohub.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inarixdono.forohub.infra.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Transactional
    public Course createCourse(IncomingCourseDTO course) {
        return repository.save(new Course(course));
    }

    @Transactional
    public Page<Course> listCourses(Pageable pagination) {
        return repository.findByStatusTrue(pagination);
    }

    @Transactional
    public Course getCourse(Integer id) {
        return repository.findByIdAndStatusTrue(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    public Course updateCourse(UpdateCourseDTO courseDTO) {
        Course course = getCourse(courseDTO.id());
        course.update(courseDTO);
        return course;
    }

    @Transactional
    public void disableCourse(Integer id) {
        Course course = getCourse(id);
        course.disable();
    }
}
