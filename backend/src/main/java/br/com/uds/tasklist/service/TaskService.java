package br.com.uds.tasklist.service;

import java.util.Optional;

import org.springframework.dao.DataAccessException;

import br.com.uds.tasklist.model.Task;

public interface TaskService {

	Optional<Task> findById(Long id) throws DataAccessException;
	Iterable<Task> findAll() throws DataAccessException;
	Task save(Task task) throws DataAccessException;
	Task update(Task task) throws DataAccessException;
	void delete(Task task) throws DataAccessException;
}
