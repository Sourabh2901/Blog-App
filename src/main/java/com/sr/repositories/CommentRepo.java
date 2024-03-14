package com.sr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sr.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment ,Integer>{

}
