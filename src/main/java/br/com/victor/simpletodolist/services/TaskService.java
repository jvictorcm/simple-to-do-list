package br.com.victor.simpletodolist.services;

import br.com.victor.simpletodolist.api.dtos.TaskInputDTO;
import br.com.victor.simpletodolist.exceptions.TaskNotFoundException;
import br.com.victor.simpletodolist.exceptions.TaskPutMappingCreation;
import br.com.victor.simpletodolist.exceptions.TaskValidationException;
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

/**
 * The class TaskService provides the service layer for the Task entity.
 * It contains methods for creating, reading, updating and deleting tasks.
 *
 * @author jvictorcm
 */
@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    /**
     * The findById method is used to find a task by id.
     *
     * @param id the id of the task
     * @return Task the task found
     * @throws TaskNotFoundException if the task is not found
     */
    public Task findById(UUID id) {
        Optional<Task> resultObject = taskRepository.findById(id);
        if (resultObject.isPresent()) {
            return resultObject.get();
        }
        throw new TaskNotFoundException(id);
    }

    /**
     * The deleteById method is used to delete a task by id.
     *
     * @param id the id of the task
     */
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    /**
     * The createTask method is used to create a task.
     *
     * @param taskInputDTO the task input data
     * @return Task the task created
     * @throws TaskValidationException in case of an invalid task name
     */
    public Task createTask(TaskInputDTO taskInputDTO) {
        TaskValidator.validateTaskName(taskInputDTO.getTask());
        Task newTask = new Task(taskInputDTO);
        newTask = taskRepository.save(newTask);
        return newTask;
    }

    /**
     * Returns a list of all Task objects
     *
     * @return A list of all Task objects
     */
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Update a task by id or create a new task whilst throws an Exception for that.
     *
     * @param taskInputDTO the task input dto
     * @param id           the id
     * @return Task the created task
     * @throws TaskPutMappingCreation  in case of not finding a task with the provided id
     * @throws TaskValidationException in case of an invalid task name
     */
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
