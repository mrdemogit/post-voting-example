package com.example.postservice.repository;

import com.example.postservice.model.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl extends BaseReposistory<Post, String> implements PostRepository {

}
