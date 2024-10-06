package com.fatihblog.comments.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Data
@NoArgsConstructor
@Getter
@Setter
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
