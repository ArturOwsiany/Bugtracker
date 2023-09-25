package com.artur.bugtracker.controller;

import com.artur.bugtracker.database.repository.IssueRepository;
import com.artur.bugtracker.database.repository.ProjectRepository;
import com.artur.bugtracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueService issueService;


    @GetMapping("")
    public String fetchIssues(Model model,
                              @RequestParam(name = "assignee", required = false) String assignee,
                              @RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "projectId", required = false) String projectId) {
        model.addAttribute("issues", issueService.filterIssues(assignee, status, projectId));
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("selectedAssignee", assignee);
        model.addAttribute("selectedProjectId", projectId);
        model.addAttribute("selectedStatus", status);
        return "issues";
    }

}
