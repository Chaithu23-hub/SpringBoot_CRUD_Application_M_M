package com.manytomany.testingmanytomany.service;

import com.manytomany.testingmanytomany.entity.Developer;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


import java.util.List;

public interface DeveloperServiceInterface {
    Developer getDeveloperById(Long id);
    Developer saveDeveloper(Developer developer);
    void deleteDeveloper(Long id);

    List<Developer> getAllDevelopers(org.springframework.data.domain.Pageable pageable);
}
