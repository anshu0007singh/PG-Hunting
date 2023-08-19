package com.PgHunting.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    long id;

    String title;

    String Content;

    String description;

    long likes;

    List<CommentResponseDto> comments;
}
