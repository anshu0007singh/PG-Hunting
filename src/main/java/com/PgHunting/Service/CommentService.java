package com.PgHunting.Service;

import com.PgHunting.util.CommentRequestDto;
import com.PgHunting.util.CommentResponseDto;

public interface CommentService {

    CommentResponseDto createNewComment(long postId,CommentRequestDto commentRequestDto);

    CommentResponseDto getCommentById(long commentId);

    long likeCommentById(long commentId);

    long dislikeCommentById(long commentId);

    CommentResponseDto updateComment(long commentId, CommentRequestDto commentRequestDto);

    String deleteComment(long commentId);

}
