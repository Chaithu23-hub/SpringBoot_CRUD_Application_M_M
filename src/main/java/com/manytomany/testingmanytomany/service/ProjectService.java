package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.dto.ProjectDTO;
import com.manytomany.testingmanytomany.entity.Developer;
import com.manytomany.testingmanytomany.entity.Project;
import com.manytomany.testingmanytomany.exception.DeveloperNotFoundException;
import com.manytomany.testingmanytomany.exception.ProjectNotFoundException;
import com.manytomany.testingmanytomany.repository.DeveloperRepository;
import com.manytomany.testingmanytomany.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProjectService implements ProjectServiceInterface{
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    public ProjectService(ProjectRepository projectRepository, DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
    }

    public List<ProjectDTO> getAllProjectsDTOs() {
        List<Project> projects = projectRepository.findAllWithDevelopers();
        return projects.stream()
                .map(this::toProjectDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByDeveloperId(Long developerId) {
        List<Project> projects = projectRepository.findAllByDeveloperId(developerId);
        return projects.stream()
                .map(this::toProjectDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO toProjectDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setProjectTitle(project.getProjectTitle());
        Set<Long> developerIds = project.getDevelopers()
                .stream()
                .map(Developer::getId)
                .collect(Collectors.toSet());
        dto.setDeveloperIds(developerIds);
        return dto;
    }

    public Project fromProjectDTO(ProjectDTO dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setProjectTitle(dto.getProjectTitle());

        Set<Developer> developers = new HashSet<>();
        if (dto.getDeveloperIds() != null) {
            for (Long devId : dto.getDeveloperIds()) {
                Developer dev = developerRepository.findById(devId)
                        .orElseThrow(() -> new DeveloperNotFoundException("Developer not found: " + devId));
                developers.add(dev);
            }
        }
        project.setDevelopers(developers);
        return project;
    }


    public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        existingProject.setProjectTitle(projectDTO.getProjectTitle());

        Set<Developer> developers = projectDTO.getDeveloperIds().stream()
                .map(id -> developerRepository.findById(id)
                        .orElseThrow(() -> new DeveloperNotFoundException("Developer not found: " + id)))
                .collect(Collectors.toSet());
        existingProject.setDevelopers(developers);

        Project updatedProject = projectRepository.save(existingProject);
        return toProjectDTO(updatedProject);
    }



    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO getProjectDTOById(Long id) {
        Project project = projectRepository.findByIdWithDevelopers(id);
        if (project == null) {
            throw new ProjectNotFoundException("Project not found with id " + id);
        }
        return toProjectDTO(project);
    }

}
