package com.inarixdono.forohub.domain.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inarixdono.forohub.domain.course.Course;
import com.inarixdono.forohub.domain.course.CourseService;
import com.inarixdono.forohub.domain.user.User;
import com.inarixdono.forohub.domain.user.UserService;
import com.inarixdono.forohub.infra.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Transactional
    public Topic createTopic(IncomingTopicDTO topic) {
        User user = userService.getUser(topic.userId());
        Course course = courseService.getCourse(topic.courseId());
        return repository.save(new Topic(topic, user, course));
    }

    @Transactional
    public Page<Topic> listTopics(Pageable pagination) {
        return repository.findByStatusTrue(pagination);
    }

    @Transactional
    public Topic getTopic(Integer id) {
        return repository.findByIdAndStatusTrue(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    public Topic updateTopic(UpdateTopicDTO topicDTO) {
        Topic topic = repository.getReferenceById(topicDTO.id());
        topic.update(topicDTO);
        return topic;
    }

    @Transactional
    public void deleteTopic(Integer id) {
        Topic topic = repository.getReferenceById(id);
        topic.disable();
    }

}
