package com.deloitte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="todo")
public class Todo {

	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "task_checked")
	private boolean taskChecked;

	@Column(name = "user_name")
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date date) {
		this.updatedDate = date;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isTaskChecked() {
		return taskChecked;
	}

	public void setTaskChecked(boolean taskChecked) {
		this.taskChecked = taskChecked;
	}



}

