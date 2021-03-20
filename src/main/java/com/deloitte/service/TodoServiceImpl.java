package com.deloitte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.model.Todo;
import com.deloitte.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository todoRepo;

	/**
	 * To get the task list.
	 */
	public List<Todo> getList(String username) {
		List<Todo> todo = new ArrayList<Todo>();
		List<Todo> userOpt = todoRepo.findAllByUsername(username);
		if (userOpt.isEmpty())
			return todo;
		else
			return userOpt;
	}

	/**
	 * To save a task
	 */
	@Override
	public void saveTask(Todo todo) {
		todoRepo.save(todo);
	}

	/**
	 * To delete a task
	 */
	@Override
	public void deleteTask(Long taskId) {
		todoRepo.deleteById(taskId);
	}

	/**
	 * To get a task
	 */
	@Override
	public Todo getTask(Long taskId) {
		Todo todo = new Todo();
		Optional<Todo> todoList = todoRepo.findById(taskId);
		if (todoList.isPresent()) {
			return todoList.get();
		} else {
			return todo;
		}
	}

	/**
	 * To edit a task
	 */
	@Override
	public void editTask(Todo todo) {
		todoRepo.save(todo);
	}

}
