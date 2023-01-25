package br.com.victor.simpletodolist.api;

import br.com.victor.simpletodolist.api.dtos.TaskInputDTO;
import br.com.victor.simpletodolist.models.Task;
import br.com.victor.simpletodolist.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskInputDTO taskInputDTO) {
        Task resultObject = taskService.createTask(taskInputDTO);
        return new ResponseEntity(resultObject, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> listAllTasks() {
        List<Task> resultObjectList = taskService.listAllTasks();
        return new ResponseEntity(resultObjectList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> findById(@PathVariable UUID id) {
        Task resultObject = taskService.findById(id);
        return new ResponseEntity(resultObject, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@RequestBody TaskInputDTO taskInputDTO, @PathVariable UUID id) {
        Task resultObject = taskService.updateTask(taskInputDTO, id);
        return new ResponseEntity(resultObject, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteById(UUID id) {
        taskService.deleteById(id);
    }


}
