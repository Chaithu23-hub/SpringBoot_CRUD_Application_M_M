package com.manytomany.testingmanytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "projects_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectTitle;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developer_project_table",joinColumns = {
            @JoinColumn(name = "project_id",referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name = "developer_id",referencedColumnName = "id")
    })
    @JsonIgnoreProperties("developers")
    private Set<Developer> developers;
}
