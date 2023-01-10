package com.example.mypet.repository;

import com.example.mypet.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostById(Long id);

//    void deletePostById(Long id);
}
