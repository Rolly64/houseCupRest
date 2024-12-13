package org.generation.italy.brewverse.authentication.repository;


import org.generation.italy.brewverse.authentication.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
