package com.artur.bugtracker.database.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.*;

@Entity
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "person_sequence", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @ElementCollection
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "creator")
    private Set<Issue> createdIssues = new HashSet<>();
    @OneToMany(mappedBy = "assignee")
    private Set<Issue> assignedIssues = new HashSet<>();
    @OneToMany(mappedBy = "creator")
    private Set<Project> cratedProjects = new HashSet<>();
}
