package br.com.victor.simpletodolist.repositories;

import br.com.victor.simpletodolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
