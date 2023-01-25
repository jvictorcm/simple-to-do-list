package br.com.victor.simpletodolist.models;


import br.com.victor.simpletodolist.api.dtos.TaskInputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity
public class Task {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;
    private String task;

    public Task(UUID id, String task) {
        this.id = id;
        this.task = task;
    }

    public Task() {
    }

    public Task(TaskInputDTO taskInputDTO){
        this.task = taskInputDTO.getTask();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
