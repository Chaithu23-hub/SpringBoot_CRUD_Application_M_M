package com.manytomany.testingmanytomany.repository;

import com.manytomany.testingmanytomany.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {

}
