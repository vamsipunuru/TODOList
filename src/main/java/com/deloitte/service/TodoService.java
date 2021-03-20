package com.deloitte.service;

import java.util.List;

import com.deloitte.model.Todo;

public interface TodoService {

	public List<Todo> getList(String username);

	public void saveTask(Todo todo);

	public void deleteTask(Long taskId);

	public Todo getTask(Long taskId);

	public void editTask(Todo todo);

}
