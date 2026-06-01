package com.tasktracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TaskManagerTest {
	//1. Happy Path
	@Test
	void shouldAddTasktoList() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Learn Java", Priority.HIGH, LocalDate.now());
		
		assertEquals(1, tm.getTasks().size());
		
	}
	
	@Test 
	void shouldStoreCorrectTaskTitle () {
		TaskManager tm = new TaskManager();
		String expectedTitle = "Learn GIT";
		
		tm.createTask(expectedTitle, Priority.HIGH, LocalDate.now());
		String actualTitle = tm.getTasks().get(0).getTitle();
		
		assertEquals(expectedTitle, actualTitle);
		
	}
	
	@Test 
	void shouldAddMultipleTasks() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Learn Java", Priority.HIGH, LocalDate.now());
		tm.createTask("Learn JavaScript", Priority.HIGH, LocalDate.now());
		tm.createTask("Learn GIT", Priority.HIGH, LocalDate.now());
		
		assertEquals(3, tm.getTasks().size());

	}
	
	@Test
	void shouldPreserveTaskOrder() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Learn Java", Priority.HIGH, LocalDate.now());
		tm.createTask("Learn JavaScript", Priority.HIGH, LocalDate.now());

		assertEquals("Learn Java", tm.getTasks().get(0).getTitle());
		assertEquals("Learn JavaScript", tm.getTasks().get(1).getTitle());

	}
	
	@Test
	void shouldMarkTaskAsCompleted() {
		TaskManager tm = new TaskManager();

		tm.createTask("Learn Java", Priority.HIGH, LocalDate.now());
		tm.markTaskCompleted(0);
		
		assertEquals(true, tm.getTasks().get(0).isCompleted());
	}
	
	
	// 2. Edge cases
	@Test
	void shouldReturnFalseForNegativeIndex() {
		TaskManager tm = new TaskManager();
		
		Boolean isIndexPositive = tm.markTaskCompleted(-1);
		
		assertEquals(false, isIndexPositive);
		
	}
	
	@Test
	void shouldReturnFalseForIndexOutsideList() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Learn Java", Priority.HIGH, LocalDate.now());
		tm.createTask("Learn JavaScript", Priority.HIGH, LocalDate.now());		
		Boolean isIndexPositive = tm.markTaskCompleted(2);
		
		assertEquals(false, isIndexPositive);
		
	}

}
