package com.tasktracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
	
	@Test
	void shouldAddTasktoList() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Java lernen");
		
		assertEquals(1, tm.getTasks().size());
		
	}
	
	@Test 
	void shouldStoreCorrectTaskTitle () {
		TaskManager tm = new TaskManager();
		String expectedTitle = "GIT lernen";
		
		tm.createTask(expectedTitle);
		String actualTitle = tm.getTasks().get(0).getTitle();
		
		assertEquals(expectedTitle, actualTitle);
		
	}
	
	@Test 
	void shouldAddMultipleTasks() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Java lernen");
		tm.createTask("JavaScript lernen");
		tm.createTask("GIT lernen");
		
		assertEquals(3, tm.getTasks().size());

	}
	
	@Test
	void shouldPreserveTaskOrder() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Java lernen");
		tm.createTask("JavaScript lernen");

		assertEquals("Java lernen", tm.getTasks().get(0).getTitle());
		assertEquals("JavaScript lernen", tm.getTasks().get(1).getTitle());

	}
	
	@Test
	void shouldMarkTaskAsCompleted() {
		TaskManager tm = new TaskManager();

		tm.createTask("Java lernen");
		tm.markTaskCompleted(0);
		
		assertEquals(true, tm.getTasks().get(0).isCompleted());
	}

}
