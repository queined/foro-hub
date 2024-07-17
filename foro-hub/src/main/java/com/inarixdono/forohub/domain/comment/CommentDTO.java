package com.inarixdono.forohub.domain.comment;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inarixdono.forohub.domain.user.UserDTO;

public record CommentDTO(
        Integer id,
        String content,
        UserDTO user,
        @JsonProperty("topic_id") Integer topicId,
        @JsonProperty("created_at") LocalDateTime createdAt) {
    public CommentDTO(Comment comment) {
        this(comment.getId(), comment.getContent(), new UserDTO(comment.getUser()), comment.getTopic().getId(),
                comment.getCreatedAt());
    }
}
