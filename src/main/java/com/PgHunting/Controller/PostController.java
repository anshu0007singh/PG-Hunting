package com.PgHunting.Controller;

import com.PgHunting.Service.PostService;
import com.PgHunting.util.PostRequestDto;
import com.PgHunting.util.PostResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Post")
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/createPost")
    public ResponseEntity<PostResponseDto> createNewPost(@RequestBody PostRequestDto postRequestDto){
        return new ResponseEntity<>(postService.createNewPost(postRequestDto), HttpStatus.OK);
    }

    @GetMapping("getPostById/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("id") long postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @PostMapping("/likePostById/{id}")
    public ResponseEntity<Long> likePostById(@PathVariable("id") long postId){
        return new ResponseEntity<>(postService.likePostById(postId),HttpStatus.OK);
    }

    @PostMapping("/dislikePostById/{id}")
    public ResponseEntity<Long> dislikePostById(@PathVariable("id") long postId){
        return new ResponseEntity<>(postService.dislikePostById(postId),HttpStatus.OK);
    }

    @PutMapping("updatePostById/{id}")
    public ResponseEntity<PostResponseDto> updatePostById(long postId,@RequestBody PostRequestDto postRequestDto){
        return new ResponseEntity<>(postService.updatePostById(postId,postRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/deletePostById/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long postId){
        return new ResponseEntity<>(postService.deletePostById(postId),HttpStatus.OK);
    }

}
