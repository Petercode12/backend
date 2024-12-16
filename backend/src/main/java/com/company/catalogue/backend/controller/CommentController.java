package com.company.catalogue.backend.controller;


import com.company.catalogue.backend.dto.CommentDTO;
import com.company.catalogue.backend.model.Comment;
import com.company.catalogue.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){ this.commentService =  commentService;};

    // Get a comment by its id provided as a query param e.g. <url-here>?id=3
    @GetMapping("")
    public Comment comment(@RequestParam int id){
        Optional<Comment> newcomment = commentService.getCommentsForStory(id);
        return (Comment) newcomment.orElse(null);
    }

    // Get all comments from the database
    @GetMapping("/all")
    public List<Comment> getAllComments(){ return commentService.getAll();};

    // Add new story to the database. Data is passed in through raw json.
    @PostMapping("/add")
    public void addComment(@RequestBody CommentDTO new_comment){
        commentService.addComment(new_comment);
    }

    // Update a comment by its ID
    @PutMapping("/update")
    public void updateComment(@RequestBody CommentDTO new_comment, @RequestParam int id){
        commentService.updateComment(new_comment, id);
    }

    // Delete a comment by its ID
    @DeleteMapping("/delete")
    public void deleteComment(@RequestParam int id){
        commentService.deleteComment(id);
    }
}