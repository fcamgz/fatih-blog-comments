package com.fatihblog.comments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME  = "comments_sequence";

    @Id
    private Long id;
    private String postId;
    private String username;
    private String comment;
    private LocalDateTime date;
}
