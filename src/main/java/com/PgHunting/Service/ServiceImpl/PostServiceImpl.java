package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Entity.Post;
import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Repository.PostRepository;
import com.PgHunting.Service.PostService;
import com.PgHunting.util.PostRequestDto;
import com.PgHunting.util.PostResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostResponseDto createNewPost(PostRequestDto postRequestDto) {
        Post post = modelMapper.map(postRequestDto, Post.class);
        postRepository.save(post);
        return modelMapper.map(post, PostResponseDto.class);
    }

    @Override
    public PostResponseDto getPostById(long postId) {
        Post post =  postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Post with id: "+postId+" not found"));
        return modelMapper.map(post, PostResponseDto.class);
    }

    @Override
    public long likePostById(long postId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Post with id: "+postId+" not found"));
        existingPost.setLikes(existingPost.getLikes()+1);
        postRepository.save(existingPost);
        return existingPost.getLikes();
    }

    @Override
    public long dislikePostById(long postId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Post with id: "+postId+" not found"));
        existingPost.setLikes(existingPost.getLikes()-1);
        postRepository.save(existingPost);
        return existingPost.getLikes();
    }

    @Override
    public PostResponseDto updatePostById(long postId, PostRequestDto postRequestDto) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Post with id: "+postId+" not found"));
        existingPost.setDescription(postRequestDto.getDescription());
        existingPost.setContent(postRequestDto.getContent());
        existingPost.setTitle(postRequestDto.getTitle());
        postRepository.save(existingPost);
        return modelMapper.map(existingPost, PostResponseDto.class);
    }

    @Override
    public String deletePostById(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Post with id: "+postId+" not found"));
        return "post deleted Succesfully";
    }
}
