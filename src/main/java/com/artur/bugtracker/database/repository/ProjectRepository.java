package com.artur.bugtracker.database.repository;

import com.artur.bugtracker.database.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

