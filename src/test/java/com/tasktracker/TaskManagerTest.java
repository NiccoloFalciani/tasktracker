package com.tasktracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TaskManagerTest {
	//1. Happy Path
	@Test
	void shouldAddTasktoList() {
		TaskManager tm = new TaskManager();
		
		tm.createTask(TaskType.STANDARD, "Learn Java", Priority.HIGH, LocalDate.now(), null, null, null);
		
		assertEquals(1, tm.getTasks().size());
		
	}
	
	@Test 
	void shouldStoreCorrectTaskTitle () {
		TaskManager tm = new TaskManager();
		String expectedTitle = "Learn GIT";
		
		tm.createTask(TaskType.STANDARD, expectedTitle, Priority.HIGH, LocalDate.now(), null, null, null);
		String actualTitle = tm.getTasks().get(0).getTitle();
		
		assertEquals(expectedTitle, actualTitle);
		
	}
	
	@Test 
	void shouldAddMultipleTasks() {
		TaskManager tm = new TaskManager();
		
		tm.createTask(TaskType.STANDARD, "Learn Java", Priority.HIGH, LocalDate.now(), null, null, null);
		tm.createTask(TaskType.STANDARD, "Learn JavaScript", Priority.HIGH, LocalDate.now(), null, null, null);
		tm.createTask(TaskType.STANDARD, "Learn GIT", Priority.HIGH, LocalDate.now(), null, null, null);
		
		assertEquals(3, tm.getTasks().size());

	}
	
	@Test
	void shouldPreserveTaskOrder() {
		TaskManager tm = new TaskManager();
		
		tm.createTask(TaskType.STANDARD, "Learn Java", Priority.HIGH, LocalDate.now(), null, null, null);
		tm.createTask(TaskType.STANDARD, "Learn JavaScript", Priority.HIGH, LocalDate.now(), null, null, null);

		assertEquals("Learn Java", tm.getTasks().get(0).getTitle());
		assertEquals("Learn JavaScript", tm.getTasks().get(1).getTitle());

	}
	
	@Test
	void shouldMarkTaskAsCompleted() {
		TaskManager tm = new TaskManager();

		tm.createTask(TaskType.STANDARD, "Learn Java", Priority.HIGH, LocalDate.now(), null, null, null);
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
		
		tm.createTask(TaskType.STANDARD, "Learn Java", Priority.HIGH, LocalDate.now(), null, null, null);
		tm.createTask(TaskType.STANDARD, "Learn JavaScript", Priority.HIGH, LocalDate.now(), null, null, null);	
		Boolean isIndexPositive = tm.markTaskCompleted(2);
		
		assertEquals(false, isIndexPositive);
		
	}

}
