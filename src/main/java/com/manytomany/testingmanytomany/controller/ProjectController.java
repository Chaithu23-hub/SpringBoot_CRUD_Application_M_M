package com.manytomany.testingmanytomany.controller;

import com.manytomany.testingmanytomany.dto.ProjectDTO;
import com.manytomany.testingmanytomany.service.ProjectService;
import com.manytomany.testingmanytomany.service.ProjectServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectServiceInterface projectServiceInterface;

    public ProjectController(ProjectServiceInterface projectServiceInterface) {
        this.projectServiceInterface = projectServiceInterface;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectServiceInterface.getAllProjectsDTOs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectServiceInterface.getProjectDTOById(id));
    }
    @GetMapping("/by-developer/{developerId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByDeveloperId(@PathVariable Long developerId) {
        List<ProjectDTO> projectDTOs = projectServiceInterface.getProjectsByDeveloperId(developerId);
        return ResponseEntity.ok(projectDTOs);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO saved = projectServiceInterface.toProjectDTO(
                projectServiceInterface.saveProject(projectServiceInterface.fromProjectDTO(projectDTO))
        );
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updated = projectServiceInterface.updateProject(id, projectDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectServiceInterface.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}