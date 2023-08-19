package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Entity.Comment;
import com.PgHunting.Entity.Post;
import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Repository.CommentRepository;
import com.PgHunting.Repository.PostRepository;
import com.PgHunting.Service.CommentService;
import com.PgHunting.util.CommentRequestDto;
import com.PgHunting.util.CommentResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentResponseDto createNewComment(long postId,CommentRequestDto commentRequestDto) {
        Comment comment = modelMapper.map(commentRequestDto,Comment.class);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"post with this id: " + postId + " doesnot exist"));

        comment.setPost(post);
        comment.setLikes(0);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto getCommentById(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"comment with this id: " + commentId + " doesnot exist"));
        return modelMapper.map(comment,CommentResponseDto.class);
    }

    @Override
    public long likeCommentById(long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"comment with this id: " + commentId + " doesnot exist"));
        existingComment.setLikes(existingComment.getLikes()+1);
        commentRepository.save(existingComment);
        return existingComment.getLikes();
    }

    @Override
    public long dislikeCommentById(long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"comment with this id: " + commentId + " doesnot exist"));
        existingComment.setLikes(existingComment.getLikes()-1);
        commentRepository.save(existingComment);
        return existingComment.getLikes();
    }

    @Override
    public CommentResponseDto updateComment(long commentId, CommentRequestDto commentRequestDto) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"comment with this id: " + commentId + " doesnot exist"));
        existingComment.setBody(commentRequestDto.getBody());
        existingComment.setLikes(0);
        commentRepository.save(existingComment);
        return modelMapper.map(existingComment,CommentResponseDto.class);
    }

    @Override
    public String deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"comment with this id: " + commentId + " doesnot exist"));
        commentRepository.delete(comment);
        return "commentDeleted Succesfully";
    }
}
