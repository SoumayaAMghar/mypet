package com.example.mypet.service;

import com.example.mypet.exception.PostNotFoundException;
import com.example.mypet.models.Post;
import com.example.mypet.repository.PostRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Post addPost(Post post){
        return postRepository.save(post);
    }
    public List<Post> finAllPost(){
        return postRepository.findAll();
    }
    public Post updatePost(Post post){
        return postRepository.save(post);
    }
    public Post findPostById(Long id){
        return postRepository.findPostById(id).orElseThrow(() -> new PostNotFoundException("Post by id "+ id + " was not flound"));
    }
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
}
