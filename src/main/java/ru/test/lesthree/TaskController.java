package ru.test.lesthree;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(@PathVariable("id") UUID id) {
        Task task = taskService.getTask(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        return new ResponseEntity<>(taskService.addTask(newTask),
                HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @RequestBody
    Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


//    @GetMapping
//    public List<Task> getAllTasks() {
//        return taskService.getAllTasks();
//    }
//
//    @GetMapping("/{id}")
//    public Task getById(@PathVariable UUID uuid) {
//        return taskService.getTask(uuid);
//    }
//
//    @PostMapping
//    public Task addById(@PathVariable Task task) {
//        return taskService.addTask(task);
//    }
//
//    @PutMapping("/{id}")
//    public Task update(@PathVariable UUID uuid, @PathVariable Task task) {
//        return taskService.updateTask(uuid, task);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable UUID uuid) {
//        taskService.deleteTask(uuid);
//    }
//}

