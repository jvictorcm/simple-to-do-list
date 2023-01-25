package br.com.victor.simpletodolist.exceptions;

import br.com.victor.simpletodolist.models.Task;

public class TaskPutMappingCreation extends RuntimeException {
    private Task createdTask;

    public TaskPutMappingCreation(Task createdTask) {
        this.createdTask = createdTask;
    }

    public Task getCreatedTask() {
        return createdTask;
    }
}
