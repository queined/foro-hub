package com.inarixdono.forohub.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import com.inarixdono.forohub.domain.comment.Comment;
import com.inarixdono.forohub.domain.course.Course;
import com.inarixdono.forohub.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topic")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createdAt;
    private Boolean status = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Topic(IncomingTopicDTO topic, User user, Course course) {
        this.subject = topic.subject();
        this.content = topic.content();
        this.createdAt = LocalDateTime.now();
        this.user = user;
        this.course = course;
    }

    public void update(UpdateTopicDTO topic) {
        if (topic.subject() != null) {
            this.subject = topic.subject();
        }
        if (topic.content() != null) {
            this.content = topic.content();
        }
    }

    public void disable() {
        this.status = false;
    }
}
