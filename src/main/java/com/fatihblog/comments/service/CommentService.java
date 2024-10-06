package com.fatihblog.comments.service;

import com.fatihblog.comments.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private final MongoOperations mongoOperations;
    @Autowired
    private final SequenceGeneratorService sequenceGeneratorService;

    public CommentService(MongoOperations mongoOperations, SequenceGeneratorService sequenceGeneratorService) {
        this.mongoOperations = mongoOperations;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public Comment createComment(Comment comment) {
        Comment commentBuilder;

        commentBuilder = Comment.builder()
                .id(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME))
                .postId(comment.getPostId())
                .username(comment.getUsername())
                .comment(comment.getComment())
                .date(LocalDateTime.now())
                .build();

        return mongoOperations.save(commentBuilder, "comments");
    }

    public List<Comment> getCommentsByPost(String postId) {
        Query query = new Query().addCriteria(Criteria.where("postId").is(postId)).with(Sort.by(Sort.Order.desc("date")));
        return mongoOperations.find(query, Comment.class);
    }

    public void deleteCommentsFromPost(String postId) {
        Query query = new Query().addCriteria(Criteria.where("postId").is(postId));
        mongoOperations.findAllAndRemove(query, Comment.class);
    }
}
