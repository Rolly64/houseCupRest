package org.generation.italy.houseCupRest.authentication.repository;


import org.generation.italy.houseCupRest.authentication.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
