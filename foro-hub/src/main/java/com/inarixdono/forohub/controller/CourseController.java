package com.inarixdono.forohub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.inarixdono.forohub.domain.course.CourseDTO;
import com.inarixdono.forohub.domain.course.CourseService;
import com.inarixdono.forohub.domain.course.IncomingCourseDTO;
import com.inarixdono.forohub.domain.course.UpdateCourseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> listCourses(Pageable pagination) {
        return ResponseEntity.ok(service.listCourses(pagination).map(CourseDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Integer id) {
        return ResponseEntity.ok(new CourseDTO(service.getCourse(id)));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid IncomingCourseDTO course, UriComponentsBuilder builder) {
        CourseDTO courseDTO = new CourseDTO(service.createCourse(course));
        URI uri = UriBuilder.buildForId(builder, "/courses/{id}", courseDTO.id().toString());
        return ResponseEntity.created(uri).body(courseDTO);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody @Valid UpdateCourseDTO course) {
        return ResponseEntity.ok(new CourseDTO(service.updateCourse(course)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> disableCourse(@PathVariable Integer id) {
        service.disableCourse(id);
        return ResponseEntity.noContent().build();
    }

}
