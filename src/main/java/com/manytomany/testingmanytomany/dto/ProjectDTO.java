package com.manytomany.testingmanytomany.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Data
@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String projectTitle;

    private Set<Long> developerIds;
}