package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.entity.Project;

import java.util.List;

public interface ProjectServiceInterface {
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    Project saveProject(Project project);
    void deleteProject(Long id);

}
