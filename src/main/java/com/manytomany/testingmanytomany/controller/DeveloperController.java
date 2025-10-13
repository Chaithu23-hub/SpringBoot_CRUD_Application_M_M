package com.manytomany.testingmanytomany.controller;

import com.manytomany.testingmanytomany.entity.Developer;
import com.manytomany.testingmanytomany.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        List<Developer> developers = developerService.getAllDevelopers();
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id) {
        Developer developer = developerService.getDeveloperById(id);
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        Developer savedDeveloper = developerService.saveDeveloper(developer);
        return new ResponseEntity<>(savedDeveloper, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer developerDetails) {
        Developer existingDeveloper = developerService.getDeveloperById(id);

        existingDeveloper.setName(developerDetails.getName());

        Developer updatedDeveloper = developerService.saveDeveloper(existingDeveloper);
        return new ResponseEntity<>(updatedDeveloper, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        developerService.deleteDeveloper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
