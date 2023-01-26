package br.com.victor.simpletodolist.services;

import br.com.victor.simpletodolist.api.dtos.TaskInputDTO;
import br.com.victor.simpletodolist.exceptions.TaskNotFoundException;
import br.com.victor.simpletodolist.exceptions.TaskPutMappingCreation;
import br.com.victor.simpletodolist.models.Task;
import br.com.victor.simpletodolist.repositories.TaskRepository;
import br.com.victor.simpletodolist.validators.TaskValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    public Task findById(UUID id) {
        Optional<Task> resultObject = taskRepository.findById(id);
        if (resultObject.isPresent()) {
            return resultObject.get();
        }
        throw new TaskNotFoundException(id);
    }

    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    public Task createTask(TaskInputDTO taskInputDTO) {
        TaskValidator.validateTaskName(taskInputDTO.getTask());
        Task newTask = new Task(taskInputDTO);
        newTask = taskRepository.save(newTask);
        return newTask;
    }

    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(TaskInputDTO taskInputDTO, UUID id) {
        TaskValidator.validateTaskName(taskInputDTO.getTask());
        Task resultObject = taskRepository.findById(id).map(task -> {
            task.setTask(taskInputDTO.getTask());
            taskRepository.save(task);
            return task;
        }).orElseThrow(() -> {
            Task newTask = new Task(taskInputDTO);
            newTask = taskRepository.save(newTask);
            throw new TaskPutMappingCreation(newTask);
        });
        return resultObject;
    }
}
