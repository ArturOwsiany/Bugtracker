package com.artur.bugtracker.database.entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    @SequenceGenerator(name = "project_generator", sequenceName = "project_sequence", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private String description;
    @Column(nullable = false)
    private String code;
    private Boolean enabled = true;
    @OneToMany(mappedBy = "project")
    private Set<Issue> issues = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person creator;

}
