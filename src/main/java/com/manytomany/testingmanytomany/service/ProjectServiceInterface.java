package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.dto.ProjectDTO;
import com.manytomany.testingmanytomany.entity.Project;

import java.util.List;

public interface ProjectServiceInterface {
    List<ProjectDTO> getAllProjectsDTOs();
    List<ProjectDTO> getProjectsByDeveloperId(Long developerId);
    Project fromProjectDTO(ProjectDTO dto);
    ProjectDTO toProjectDTO(Project project);
    ProjectDTO getProjectDTOById(Long id);
    Project updateProject(Long projectId, ProjectDTO projectDTO);
    Project saveProject(Project project);
    void deleteProject(Long id);

}
