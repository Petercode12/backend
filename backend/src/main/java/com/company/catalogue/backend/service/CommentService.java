package com.company.catalogue.backend.service;

import com.company.catalogue.backend.dto.CommentDTO;
import com.company.catalogue.backend.model.Comment;
import com.company.catalogue.backend.model.Story;
import com.company.catalogue.backend.model.User;
import com.company.catalogue.backend.repository.CommentRepository;
import com.company.catalogue.backend.repository.StoryRepository;
import com.company.catalogue.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;

    @Autowired
    CommentService(CommentRepository commentRepository, UserRepository userRepository, StoryRepository storyRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.storyRepository = storyRepository;
    };

    public Optional<Comment> getCommentsForStory( long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment addComment(CommentDTO comment){
        Optional<User> optionalUser = userRepository.findById(comment.getUser_id());
        Optional<Story> optionalStory = storyRepository.findById(comment.getStory_id());

        User user = optionalUser.orElseThrow();
        Story story = optionalStory.orElseThrow();

        Comment newComment = new Comment();

        newComment.setUser(user);
        newComment.setStory(story);
        newComment.setContent(comment.getContent());
        newComment.setDatePosted(new Date());

        return commentRepository.save(newComment);
    }

    public void deleteComment(long id){
        commentRepository.deleteById(id);
    }

    public Comment updateComment(CommentDTO newComment, long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(newComment.getUser_id());
        Optional<Story> optionalStory = storyRepository.findById(newComment.getStory_id());

        User user = optionalUser.orElseThrow();
        Story story = optionalStory.orElseThrow();

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            // Update the fields that should be updated
            comment.setUser(user);
            comment.setStory(story);
            comment.setContent(newComment.getContent());
            // Save new comment to database
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }



}