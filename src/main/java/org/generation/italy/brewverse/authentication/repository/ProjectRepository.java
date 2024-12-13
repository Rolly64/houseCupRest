package org.generation.italy.brewverse.authentication.repository;


import org.generation.italy.brewverse.authentication.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
