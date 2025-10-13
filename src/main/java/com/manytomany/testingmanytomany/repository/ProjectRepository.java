package com.manytomany.testingmanytomany.repository;

import com.manytomany.testingmanytomany.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

}
