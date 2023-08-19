package com.PgHunting.Service;

import com.PgHunting.util.PostRequestDto;
import com.PgHunting.util.PostResponseDto;

import java.util.List;

public interface PostService {

    PostResponseDto createNewPost(PostRequestDto postRequestDto);

    PostResponseDto getPostById(long postId);

    long likePostById(long postId);

    long dislikePostById(long postId);

    PostResponseDto updatePostById(long postId, PostRequestDto postRequestDto);

    String deletePostById(long postId);

}
