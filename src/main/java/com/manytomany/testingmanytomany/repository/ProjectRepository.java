package com.manytomany.testingmanytomany.repository;

import com.manytomany.testingmanytomany.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.developers")
    List<Project> findAllWithDevelopers();

    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.developers WHERE p.id = :id")
    Project findByIdWithDevelopers(@Param("id") Long id);

    @Query("SELECT p FROM Project p JOIN p.developers d WHERE d.id = :developerId")
    List<Project> findAllByDeveloperId(@Param("developerId") Long developerId);

}


