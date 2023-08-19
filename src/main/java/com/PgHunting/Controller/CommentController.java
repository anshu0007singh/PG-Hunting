package com.PgHunting.Controller;

import com.PgHunting.Service.CommentService;
import com.PgHunting.util.CommentRequestDto;
import com.PgHunting.util.CommentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/createComment/{id}")
    public ResponseEntity<CommentResponseDto> createNewPost(@PathVariable("id") long postId, @RequestBody CommentRequestDto commentRequestDto){
        return new ResponseEntity<>(commentService.createNewComment(postId,commentRequestDto), HttpStatus.OK);
    }

    @GetMapping("/getCommentById/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable("id") long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId),HttpStatus.OK);
    }

    @PostMapping("/likeCommentById/{id}")
    public ResponseEntity<Long> likeCommentById(@PathVariable("id") long commentId){
        return new ResponseEntity<>(commentService.likeCommentById(commentId),HttpStatus.OK);
    }

    @PostMapping("/dislikeCommentById/{id}")
    public ResponseEntity<Long> dislikeCommentById(@PathVariable("id") long commentId){
        return new ResponseEntity<>(commentService.dislikeCommentById(commentId),HttpStatus.OK);
    }

    @PutMapping("/updateCommentById/{id}")
    public ResponseEntity<CommentResponseDto> updatePostById(@PathVariable("id") long commentId,@RequestBody CommentRequestDto commentRequestDto){
        return new ResponseEntity<>(commentService.updateComment(commentId,commentRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCommentById/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") long commentId){
        return new ResponseEntity<>(commentService.deleteComment(commentId),HttpStatus.OK);
    }
}
