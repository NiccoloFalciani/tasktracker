package com.tasktracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
	
	@Test
	void testAddTask() {
		TaskManager tm = new TaskManager();
		
		tm.createTask("Java lernen");
		
		assertEquals(1, tm.getTasks().size());
		
	}

}
