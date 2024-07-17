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

import com.inarixdono.forohub.domain.topic.IncomingTopicDTO;
import com.inarixdono.forohub.domain.topic.TopicDTO;
import com.inarixdono.forohub.domain.topic.TopicService;
import com.inarixdono.forohub.domain.topic.UpdateTopicDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService service;

    @GetMapping
    public ResponseEntity<Page<TopicDTO>> listTopics(Pageable pagination) {
        return ResponseEntity.ok(service.listTopics(pagination).map(TopicDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopic(@PathVariable Integer id) {
        return ResponseEntity.ok(new TopicDTO(service.getTopic(id)));
    }

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid IncomingTopicDTO topic, UriComponentsBuilder builder) {
        TopicDTO topicDTO = new TopicDTO(service.createTopic(topic));
        URI uri = UriBuilder.buildForId(builder, "/topics/{id}", topicDTO.id().toString());
        return ResponseEntity.created(uri).body(topicDTO);
    }

    @PutMapping
    public ResponseEntity<TopicDTO> updateTopic(@RequestBody @Valid UpdateTopicDTO topic) {
        return ResponseEntity.ok(new TopicDTO(service.updateTopic(topic)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicDTO> deleteTopic(@PathVariable Integer id) {
        service.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
