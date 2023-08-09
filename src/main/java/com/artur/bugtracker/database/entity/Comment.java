package com.artur.bugtracker.database.entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    @SequenceGenerator(name = "comment_generator", sequenceName = "comment_sequence", allocationSize = 1)
    private long id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Person author;
    private String content;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
}
