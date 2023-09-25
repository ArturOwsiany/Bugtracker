package com.artur.bugtracker.service;

import com.artur.bugtracker.database.entity.Issue;
import com.artur.bugtracker.database.entity.Person;
import com.artur.bugtracker.database.entity.Project;
import com.artur.bugtracker.database.entity.Status;
import com.artur.bugtracker.database.repository.IssueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class IssueServiceTest {

    @Mock
    IssueRepository issueRepository;

    @InjectMocks
    IssueService issueService;

    @Test
    void filterIssuesByAssignee() {
        String filterUsernameParameter = "Aaa";
        Mockito.when(issueRepository.findAll()).thenReturn(createIssues());

        List<Issue> result = issueService.filterIssues(filterUsernameParameter.toUpperCase(), null, null);

        Assertions.assertThat(result).hasSize(2);
        result.forEach(issue -> Assertions.assertThat(issue.getAssignee().getUsername()).isEqualTo(filterUsernameParameter));
    }

    @Test
    void filterIssuesByStatus() {
        Status filterStatusParameter = Status.DONE;
        Mockito.when(issueRepository.findAll()).thenReturn(createIssues());

        List<Issue> result = issueService.filterIssues(null, filterStatusParameter.name(), null);

        Assertions.assertThat(result).hasSize(2);
        result.forEach(issue -> Assertions.assertThat(issue.getStatus()).isEqualTo(filterStatusParameter));
    }

    @Test
    void filterIssuesByProjectId() {
        Long filterProjectIdParameter = 2L;
        Mockito.when(issueRepository.findAll()).thenReturn(createIssues());

        List<Issue> result = issueService.filterIssues(null, null, filterProjectIdParameter.toString());

        Assertions.assertThat(result).hasSize(2);
        result.forEach(issue -> Assertions.assertThat(issue.getProject().getId()).isEqualTo(filterProjectIdParameter));
    }

    @Test
    void filterIssuesByAssigneeStatusAndProjectId() {
        String filterUsernameParameter = "Aaa";
        Status filterStatusParameter = Status.IN_PROGRESS;
        Long filterProjectIdParameter = 3L;
        Mockito.when(issueRepository.findAll()).thenReturn(createIssues());

        List<Issue> result = issueService.filterIssues(filterUsernameParameter,
                filterStatusParameter.toString(),
                filterProjectIdParameter.toString());

        Assertions.assertThat(result).hasSize(1);
        result.forEach(issue -> {
            Assertions.assertThat(issue.getAssignee().getUsername()).isEqualTo(filterUsernameParameter);
            Assertions.assertThat(issue.getStatus()).isEqualTo(filterStatusParameter);
            Assertions.assertThat(issue.getProject().getId()).isEqualTo(filterProjectIdParameter);
        });
    }

    private List<Issue> createIssues() {
        Person assignee1 = new Person();
        assignee1.setUsername("Aaa");
        Person assignee2 = new Person();
        assignee2.setUsername("Bbb");
        Person assignee3 = new Person();
        assignee3.setUsername("Ccc");

        Project project1 = new Project();
        project1.setId(1L);
        Project project2 = new Project();
        project2.setId(2L);
        Project project3 = new Project();
        project3.setId(3L);

        return List.of(createIssue(assignee1, Status.NEW, project1),
                createIssue(assignee2, Status.IN_PROGRESS, project2),
                createIssue(assignee3, Status.DONE, project3),
                createIssue(assignee1, Status.IN_PROGRESS, project3),
                createIssue(assignee2, Status.DONE, project1),
                createIssue(assignee3, Status.NEW, project2));
    }

    private Issue createIssue(Person assignee, Status status, Project project) {
        Issue issue = new Issue();
        issue.setAssignee(assignee);
        issue.setStatus(status);
        issue.setProject(project);
        return issue;
    }
}