package com.artur.bugtracker.database.repository;

import com.artur.bugtracker.database.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository <Issue,Long> {
}
