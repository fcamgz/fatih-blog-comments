package com.fatihblog.comments.controller;

import com.fatihblog.comments.model.Comment;
import com.fatihblog.comments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        try {
            Comment _comment = commentService.createComment(comment);
            return new ResponseEntity<>(_comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable String postId) {
        try {
            List<Comment> _comments = commentService.getCommentsByPost(postId);
            return new ResponseEntity<>(_comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("delete/{postId}")
    public ResponseEntity<String> deleteCommentsFromPost(@PathVariable String postId) {
        try {
            commentService.deleteCommentsFromPost(postId);
            return new ResponseEntity<>("Comments have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
