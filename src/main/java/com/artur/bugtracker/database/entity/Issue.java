package com.artur.bugtracker.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_generator")
    @SequenceGenerator(name = "issue_generator", sequenceName = "issue_sequence", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String code;
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Person assignee;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @OneToMany(mappedBy = "issue")
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
