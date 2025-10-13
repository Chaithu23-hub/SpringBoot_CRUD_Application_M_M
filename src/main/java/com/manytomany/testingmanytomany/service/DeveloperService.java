package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.entity.Developer;
import com.manytomany.testingmanytomany.exception.DeveloperNotFoundException;
import com.manytomany.testingmanytomany.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService implements DeveloperServiceInterface{
    private final DeveloperRepository developerRepository;
    public DeveloperService(DeveloperRepository developerRepository){
        this.developerRepository=developerRepository;
    }

    public List<Developer> getAllDevelopers(){
        return developerRepository.findAll();
    }

    public Developer getDeveloperById(Long id){
        return developerRepository.findById(id).orElseThrow(()->new DeveloperNotFoundException("Developer not Found"));
    }

    public Developer saveDeveloper(Developer developer){
        return developerRepository.save(developer);
    }

    public void deleteDeveloper(Long id){
        developerRepository.deleteById(id);
    }
}
