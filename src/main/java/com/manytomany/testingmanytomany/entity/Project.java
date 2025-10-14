package com.manytomany.testingmanytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "projects_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectTitle;
    @ManyToMany(mappedBy = "projects",cascade = ALL)
    @JsonIgnoreProperties("projects")
    private Set<Developer> developers;

}
