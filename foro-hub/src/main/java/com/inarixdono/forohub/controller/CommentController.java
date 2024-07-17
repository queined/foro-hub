package com.inarixdono.forohub.controller;

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

import java.net.URI;

import com.inarixdono.forohub.domain.comment.CommentDTO;
import com.inarixdono.forohub.domain.comment.CommentService;
import com.inarixdono.forohub.domain.comment.IncommingCommentDTO;
import com.inarixdono.forohub.domain.comment.UpdateCommentDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<Page<CommentDTO>> listComments(Pageable pagination) {
        return ResponseEntity.ok(service.listComments(pagination).map(CommentDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Integer id) {
        return ResponseEntity.ok(new CommentDTO(service.getComment(id)));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody @Valid IncommingCommentDTO commentDTO, UriComponentsBuilder builder) {
        CommentDTO comment = new CommentDTO(service.createComment(commentDTO));
        URI uri = UriBuilder.buildForId(builder, "/comments/{id}", comment.id().toString());
        return ResponseEntity.created(uri).body(comment);
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestBody @Valid UpdateCommentDTO commentDTO) {
        return ResponseEntity.ok(new CommentDTO(service.updateComment(commentDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
