package com.deloitte.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.model.Todo;
import com.deloitte.repository.TodoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {

	@Autowired
	private TodoServiceImpl todoService;

	@Autowired
	TodoRepository todoRepo;

	private static Todo todo = null;

	private Todo createTask() {
		Todo newTodo = new Todo();
		newTodo.setId(3L);
		newTodo.setName("First-Task");
		newTodo.setDescription("Create First task for Junit test");
		newTodo.setUsername("Vamsi");
		return todoRepo.save(newTodo);
	}

	@Transactional
	@Before
	public void setUp() {
		todo = createTask();
	}

	@Test
	@Transactional
	public void testGetTask() {
		Todo gettodo = todoService.getTask(todo.getId());
		assertThat(gettodo.getId()).isEqualTo(3L);
	}

	@Test
	@Transactional
	public void testGetList() {
		List<Todo> todoOpt = todoRepo.findAllByUsername(todo.getUsername());
		assertThat(todoOpt.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void testEditTask() {
		todo.setDescription("Task Updated");
		todo.setUpdatedDate(new Date());
		todoService.editTask(todo);
		List<Todo> rttodo = todoService.getList(todo.getUsername());
		assertThat(rttodo.get(0).getDescription()).isEqualTo("Task Updated");
	}

	@Test
	@Transactional	
	public void deleteTask() {
		todoRepo.deleteById(todo.getId());
		List<Todo> rttodo = todoService.getList(todo.getUsername());
		assertThat(rttodo.size()).isEqualTo(0);
	}
}
