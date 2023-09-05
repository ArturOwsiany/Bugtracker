package com.artur.bugtracker.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "person_sequence", allocationSize = 1)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private Boolean enabled = true;
    @ElementCollection(fetch = FetchType.EAGER)
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
