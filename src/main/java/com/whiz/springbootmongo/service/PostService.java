package com.whiz.springbootmongo.service;

import com.whiz.springbootmongo.domain.Post;
import com.whiz.springbootmongo.repository.PostRepository;
import com.whiz.springbootmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }



}