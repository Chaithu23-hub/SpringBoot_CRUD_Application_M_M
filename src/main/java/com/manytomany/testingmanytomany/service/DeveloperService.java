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
public class DeveloperService implements DeveloperServiceInterface{
    private final ProjectRepository projectRepository;

    private final DeveloperRepository developerRepository;

    public DeveloperService(ProjectRepository projectRepository, DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
    }

    public List<Developer> getAllDevelopers(){
        return developerRepository.findAll();
    }

    public Developer getDeveloperById(Long id){
        return developerRepository.findById(id).orElseThrow(()->new DeveloperNotFoundException("Developer not Found"));
    }
    public Developer updateDeveloper(Long developerId, Developer developerDetails) {
        Developer existingDeveloper = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer not found with id " + developerId));

        existingDeveloper.setName(developerDetails.getName());

        Set<Project> managedProjects = new HashSet<>();
        if (developerDetails.getProjects() != null) {
            for (Project project : developerDetails.getProjects()) {
                Project managedProject = projectRepository.findById(project.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("Project not found with id " + project.getId()));
                managedProjects.add(managedProject);
            }
        }

        existingDeveloper.setProjects(managedProjects);

        // Keep bidirectional consistency by adding developer to each project's developers set
        for (Project project : managedProjects) {
            project.getDevelopers().add(existingDeveloper);
        }

        return developerRepository.save(existingDeveloper);
    }


    public Developer saveDeveloper(Developer developer){
        return developerRepository.save(developer);
    }

    public void deleteDeveloper(Long id){
        developerRepository.deleteById(id);
    }
}
