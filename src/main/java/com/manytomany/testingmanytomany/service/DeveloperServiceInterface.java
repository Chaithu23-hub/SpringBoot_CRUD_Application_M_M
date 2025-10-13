package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.entity.Developer;

import java.util.List;

public interface DeveloperServiceInterface {
    List<Developer> getAllDevelopers();
    Developer getDeveloperById(Long id);
    Developer saveDeveloper(Developer developer);
    void deleteDeveloper(Long id);

}
