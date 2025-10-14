package com.manytomany.testingmanytomany.service;

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

@Service
public class ProjectService implements ProjectServiceInterface{
    private final ProjectRepository projectRepository;

    private final DeveloperRepository developerRepository;

    public ProjectService(ProjectRepository projectRepository, DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
    }



    public List<Project> getAllProjects(){
        return projectRepository.findAll();
        }
   public Project getProjectById(Long id){
        return projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("No projects found"));
    }
    //From here we are updating or makinmg relationship with developers and assigning them by checking whether are they are available
    public Project updateProject(Long projectId, Project projectDetails) {
        //finding project details by id
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        //updateing project deatails like project Title
        existingProject.setProjectTitle(projectDetails.getProjectTitle());

        // Fetching  managed developers from DB
        Set<Developer> managedDevelopers = new HashSet<>();
        if (projectDetails.getDevelopers() != null) {
            for (Developer dev : projectDetails.getDevelopers()) {
                Developer managedDev = developerRepository.findById(dev.getId())
                        .orElseThrow(() -> new DeveloperNotFoundException("Developer not found"));
                managedDevelopers.add(managedDev);
            }
        }

        // Setting  developers into  project
        existingProject.setDevelopers(managedDevelopers);

        // Keep bidirectional relationship consistent and we update
        for (Developer dev : managedDevelopers) {
            dev.getProjects().add(existingProject);
        }

        // Save the project (owning side is Developer, but saving project here is okay if cascade ALL is set)
        return projectRepository.save(existingProject);
    }

    public Project saveProject(Project project){
        return projectRepository.save(project);
        }
    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

}
