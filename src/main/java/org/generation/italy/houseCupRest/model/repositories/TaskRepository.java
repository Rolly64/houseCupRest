package org.generation.italy.houseCupRest.model.repositories;


import org.generation.italy.houseCupRest.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
