package com.manytomany.testingmanytomany.controller;

import com.manytomany.testingmanytomany.entity.Developer;
import com.manytomany.testingmanytomany.service.DeveloperService;
import com.manytomany.testingmanytomany.service.DeveloperServiceInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperServiceInterface developerServiceInterface;

    public DeveloperController(DeveloperServiceInterface developerServiceInterface) {
        this.developerServiceInterface = developerServiceInterface;
    }

    @GetMapping
    public ResponseEntity<List<Developer>> getAllDevelopers(
            @RequestParam int pageNo,
            @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(developerServiceInterface.getAllDevelopers(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id) {
        return ResponseEntity.ok(developerServiceInterface.getDeveloperById(id));
    }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        Developer saved = developerServiceInterface.saveDeveloper(developer);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer developer) {
        developer.setId(id);
        Developer updated = developerServiceInterface.saveDeveloper(developer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        developerServiceInterface.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }
}
