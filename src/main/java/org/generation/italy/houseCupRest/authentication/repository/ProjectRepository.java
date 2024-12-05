package org.generation.italy.houseCupRest.authentication.repository;


import org.generation.italy.houseCupRest.authentication.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
