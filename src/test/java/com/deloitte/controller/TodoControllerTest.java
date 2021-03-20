package com.deloitte.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deloitte.model.Todo;
import com.deloitte.repository.TodoRepository;
import com.deloitte.service.TodoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@MockBean
	private TodoService todoServiceMock;
	@MockBean
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
	@Before
	@Transactional
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		todo = createTask();

	}
	@Test
	@WithMockUser(username = "Vamsi",
	password = "$2a$10$sWszOXuTlN0amQi8vXp4cerb.tJUQo.4FzLAnTCsSqChsYhlLdQWW",
	authorities = "ROLE_ADMIN")
	public void testGetList() throws Exception {
		long taskId =3L;
		assertThat(this.todoServiceMock).isNotNull();
		when(todoServiceMock.getTask(3L)).thenReturn(todo);
		MvcResult result= mockMvc.perform(get("/tasks"))
				.andExpect(status().isOk())
				.andExpect(view().name("todolist"))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("todoTasks"))
				.andReturn();
		MockHttpServletResponse mockResponse=result.getResponse();
		assertThat(mockResponse.getContentType()).isEqualTo("text/html;charset=UTF-8");
	}

	@Test
	@WithMockUser(username = "Vamsi",
	password = "$2a$10$sWszOXuTlN0amQi8vXp4cerb.tJUQo.4FzLAnTCsSqChsYhlLdQWW",
	authorities = "ROLE_ADMIN")
	@Transactional
	public void testSaveTask() throws Exception {
		assertThat(this.todoServiceMock).isNotNull();
		when(todoServiceMock.getTask(3L)).thenReturn(todo);
		MvcResult result= mockMvc.perform(post("/saveTask/"))
				.andExpect(status().isOk())
				.andExpect(view().name("todolist"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("todoTasks"))
				.andReturn();
	}
}