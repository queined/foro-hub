package com.inarixdono.forohub.domain.comment;

import java.time.LocalDateTime;

import com.inarixdono.forohub.domain.topic.Topic;
import com.inarixdono.forohub.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "comment")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Boolean status = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Comment(String content, User user, Topic topic) {
        this.content = content;
        this.user = user;
        this.topic = topic;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String content) {
        this.content = content;
    }

    public void disable() {
        this.status = false;
    }
}
