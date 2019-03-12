package br.com.uds.tasklist.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.uds.tasklist.model.Task;
import br.com.uds.tasklist.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Optional<Task> findById(Long id) throws DataAccessException {
		return taskRepository.findById(id);
	}

	@Override
	public Iterable<Task> findAll() throws DataAccessException {
		return taskRepository.findAll();
	}

	@Override
	public Task save(Task task) throws DataAccessException {
		task.setCreationDate(LocalDateTime.now());
		return taskRepository.save(task);
	}

	@Override
	public Task update(Task task) throws DataAccessException {
		task.setUpdateDate(LocalDateTime.now());
		if (task.getStatus()) {
			task.setConclusionDate(LocalDateTime.now());
		}
		return taskRepository.save(task);
	}
	
	@Override
	public void delete(Task task) throws DataAccessException {
		taskRepository.delete(task);
	}

}
