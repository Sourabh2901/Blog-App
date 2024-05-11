package com.sr.Paylods.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private Integer commentId;
    private String content;
}
