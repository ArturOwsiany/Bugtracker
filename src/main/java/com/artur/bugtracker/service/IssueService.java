package com.artur.bugtracker.service;

import com.artur.bugtracker.database.entity.Issue;
import com.artur.bugtracker.database.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> filterIssues(String assignee, String status, String projectId) {
        List<Issue> issues = issueRepository.findAll();
        if (assignee != null && !assignee.isEmpty()) {
            issues = issues.stream()
                    .filter(issue -> issue.getAssignee().getUsername().toLowerCase().contains(assignee.toLowerCase())).toList();
        }
        if (status != null) {
            issues = issues.stream().filter(issue -> issue.getStatus().name().equalsIgnoreCase(status)).toList();
        }
        if (projectId != null && !projectId.isEmpty()) {
            issues = issues.stream().filter(issue -> issue.getProject().getId().equals(Long.valueOf(projectId))).toList();
        }
        return issues;
    }
}
