package com.artur.bugtracker.database.repository;

import com.artur.bugtracker.database.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
