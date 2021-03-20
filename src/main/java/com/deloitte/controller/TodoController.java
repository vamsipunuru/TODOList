package com.deloitte.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.model.Todo;
import com.deloitte.service.TodoService;

@Controller
public class TodoController {


	@Autowired
	private TodoService todoService;

	/**
	 *  Get logged in username
	 * @return username
	 */
	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		return username;
	}

	/**
	 *  Get Task List by Username
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tasks", method = RequestMethod.GET)
	public ModelAndView getList(ModelMap model) {
		List<Todo> todo = todoService.getList(getUserName());
		model.put("todoTasks", todo);
		return new ModelAndView("todolist"); 

	}

	/**
	 * To redirect to the theamleaf template file
	 * @return
	 */
	@GetMapping("/addtask")
	public String addtask() {
		return "addtask";
	}
	
	/**
	 * To redirect to the theamleaf template file
	 * @return
	 */
	@GetMapping("/removetask")
	public String removetask() {
		return "removetask";
	}
	
	/**
	 * Save Tasks
	 * @param todo
	 * @param model
	 * @return
	 */
	@PostMapping("/saveTask")
	public ModelAndView processForm(@ModelAttribute(value="todo") Todo todo,ModelMap model) {
		todo.setUsername(getUserName());
		todo.setUpdatedDate(new Date());
		todoService.saveTask(todo);
		List<Todo> rttodo = todoService.getList(getUserName());
		model.put("todoTasks", rttodo);
		return new ModelAndView("todolist"); 
	}

	/**
	 * Delete Task by task id
	 * @param taskId
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/removeTask/{taskId}")
	public ModelAndView deleteTask(@PathVariable Long taskId,ModelMap model) {
		todoService.deleteTask(taskId);
		List<Todo> rttodo = todoService.getList(getUserName());
		model.put("todoTasks", rttodo);
		return new ModelAndView("todolist"); 
	}

	/**
	 * Get Task by task id
	 * @param taskId
	 * @param model
	 * @return
	 */
	@GetMapping("/getTask/{taskId}")
	public ModelAndView getTask(@PathVariable Long taskId, ModelMap model) {

		Todo rttodo = todoService.getTask(taskId);
		ModelAndView mav = new ModelAndView("edittask");
		mav.addObject("todo",rttodo);
		return mav;
	}

	/**
	 * Edit Tasks
	 * @param todo
	 * @param model
	 * @return
	 */
	@PostMapping("/editTask")
	public ModelAndView editTask(@ModelAttribute(value="todo") Todo todo,ModelMap model) {
		todo.setUsername(getUserName());
		todo.setUpdatedDate(new Date());
		todoService.editTask(todo);
		List<Todo> rttodo = todoService.getList(getUserName());
		model.put("todoTasks", rttodo);
		return new ModelAndView("todolist"); 
	}

	/**
	 * Check/Uncheck tasks
	 * @param taskId
	 * @param model
	 * @param taskCheck
	 * @return
	 */
	@PostMapping("/taskCheck/{taskId}")
	public ModelAndView updateCheck(@PathVariable Long taskId, ModelMap model, @RequestParam(required=false, defaultValue ="false") boolean taskCheck) {

		Todo todo = todoService.getTask(taskId);
		todo.setTaskChecked(taskCheck);
		todo.setUpdatedDate(new Date());
		todoService.editTask(todo);
		List<Todo> rttodo = todoService.getList(getUserName());
		model.put("todoTasks", rttodo);
		return new ModelAndView("todolist"); 

	}

}

