package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.entity.Project;
import com.manytomany.testingmanytomany.exception.ProjectNotFoundException;
import com.manytomany.testingmanytomany.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ProjectServiceInterface{
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
        }
   public Project getProjectById(Long id){
        return projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("No projects found"));
    }
    public Project saveProject(Project project){
        return projectRepository.save(project);
        }
    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

}
