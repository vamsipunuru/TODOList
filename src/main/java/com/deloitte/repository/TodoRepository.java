package com.deloitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	List<Todo> findAllByUsername(String username);
}
