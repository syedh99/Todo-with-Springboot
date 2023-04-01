package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService 
{
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todoCounter =0;
	
	static
	{
		todos.add(new Todo(++todoCounter,"in28minutes","Learn AWS Cert",
				LocalDate.now().plusYears(1),false));
		
		todos.add(new Todo(++todoCounter,"in28minutes","Learn DevOps",
				LocalDate.now().plusYears(2),false));
		
		todos.add(new Todo(++todoCounter,"in28minutes","Learn Full Stack Development",
				LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUsername(String username)
	{
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done)
	{
		Todo todo=new Todo(++todoCounter,username,description,targetDate,done);
		todos.add(todo);
	}
	
	public void deleteById(int id)
	{
		Predicate<? super Todo> predicate = todo -> todo.getId()== id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id)
	{
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate = todo -> todo.getId()== id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
	
}
