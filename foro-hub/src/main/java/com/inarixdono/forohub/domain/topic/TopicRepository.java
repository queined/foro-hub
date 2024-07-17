package com.inarixdono.forohub.domain.topic;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{
    public Page<Topic> findByStatusTrue(Pageable pagination);
    public Optional<Topic> findByIdAndStatusTrue(Integer id);
}
