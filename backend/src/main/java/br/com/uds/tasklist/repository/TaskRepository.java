package br.com.uds.tasklist.repository;

import org.springframework.stereotype.Repository;

import br.com.uds.tasklist.model.Task;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}
