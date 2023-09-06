package com.artur.bugtracker.controller;

import com.artur.bugtracker.database.entity.Person;
import com.artur.bugtracker.database.entity.Project;
import com.artur.bugtracker.database.repository.PersonRepository;
import com.artur.bugtracker.database.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("")
    public String fetchProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/{id}")
    public String fetchProjectDetails(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id).orElseThrow();
        model.addAttribute("project", project);
        return "projectDetails";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/projects";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("editMode", false);
        model.addAttribute("project", new Project());
        return "project";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable Long id, Model model) {
        Project projectForEdit = projectRepository.findById(id).orElseThrow();
        model.addAttribute("editMode", true);
        model.addAttribute("project", projectForEdit);
        return "project";
    }

    @PostMapping("/create")
    public String creteProjectSubmit(@ModelAttribute Project project) {
        project = project.getId() == null ? createNewProject(project) : updateExistingProject(project);
        projectRepository.save(project);
        return "redirect:/projects";
    }

    private Project createNewProject(Project project) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person loggedUser = personRepository.findPersonByUsername(currentUser.getUsername());
        project.setCreator(loggedUser);
        return project;
    }

    private Project updateExistingProject(Project project) {
        Project projectForUpdate = projectRepository.findById(project.getId()).orElseThrow();
        projectForUpdate.setName(project.getName());
        projectForUpdate.setDescription(project.getDescription());
        projectForUpdate.setCode(project.getCode());
        projectForUpdate.setEnabled(project.getEnabled());
        return projectForUpdate;
    }
}
