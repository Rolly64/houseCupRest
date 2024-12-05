package org.generation.italy.houseCupRest.model.repositories;


import org.generation.italy.houseCupRest.model.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
