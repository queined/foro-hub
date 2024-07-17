package com.inarixdono.forohub.domain.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inarixdono.forohub.domain.topic.Topic;
import com.inarixdono.forohub.domain.topic.TopicService;
import com.inarixdono.forohub.domain.user.User;
import com.inarixdono.forohub.domain.user.UserService;
import com.inarixdono.forohub.infra.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Transactional
    public Comment createComment(IncommingCommentDTO commentDTO) {
        User user = userService.getUser(commentDTO.userId());
        Topic topic = topicService.getTopic(commentDTO.topicId());
        Comment comment = repository.save(new Comment(commentDTO.content(), user, topic));
        return comment;
    }

    public Page<Comment> listComments(Pageable pagination) {
        return repository.findByStatusTrue(pagination);
    }

    public Comment getComment(Integer id) {
        return repository.findByIdAndStatusTrue(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    public Comment updateComment(UpdateCommentDTO commentDTO) {
        Comment comment = this.getComment(commentDTO.id());
        comment.update(commentDTO.content());
        return comment;
    }

    @Transactional
    public void deleteComment(Integer id) {
        Comment comment = this.getComment(id);
        comment.disable();
    }

}
