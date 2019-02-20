package com.example.postservice.repository;

import com.example.postservice.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl extends BaseReposistory<Comment, String> implements CommentRepository {

}
