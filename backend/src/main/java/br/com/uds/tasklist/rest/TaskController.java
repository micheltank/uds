package br.com.uds.tasklist.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.uds.tasklist.model.Task;
import br.com.uds.tasklist.service.TaskService;

@RestController
@RequestMapping(path = "/api/tasks")
public class TaskController {
	
	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Task>> getTasks() {
		Iterable<Task> tasks = this.taskService.findAll();
		if (tasks.iterator().hasNext()){
			return new ResponseEntity<Iterable<Task>>(tasks, HttpStatus.OK);
		}
		return new ResponseEntity<Iterable<Task>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Task> getTask(@PathVariable(name = "id") Long id) {
		Optional<Task> task = this.taskService.findById(id);
		if (task.isPresent()) {
			return new ResponseEntity<Task>(task.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Task> addTask(@RequestBody @Valid Task task) {
		Task savedTask = this.taskService.save(task);
		return new ResponseEntity<Task>(savedTask, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Task> updateTask(@PathVariable(name = "id") Long id, @RequestBody @Valid Task task) {
		
		Optional<Task> currentTask = this.taskService.findById(id);
		if (!currentTask.isPresent()) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		currentTask.get().setStatus(task.getStatus());
		currentTask.get().setDescription(task.getDescription());
		this.taskService.update(currentTask.get());
		
		return new ResponseEntity<Task>(currentTask.get(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Long id) {
		Optional<Task> task = this.taskService.findById(id);
		if (!task.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.taskService.delete(task.get());
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
