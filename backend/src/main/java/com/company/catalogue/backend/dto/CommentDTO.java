package com.company.catalogue.backend.dto;



import lombok.Data;



@Data
public class CommentDTO {


    private long story_id;

    private long user_id;

    private String content;

}