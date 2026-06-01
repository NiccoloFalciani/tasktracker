package com.tasktracker;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsoleUI {
	
	public static void printTasks(ArrayList<Task> tasks) {
		if (tasks.isEmpty()) {
			System.out.println("No tasks available!");
			return;
		}
		
		for (Task task : tasks) {
			System.out.println(task);
		}
	}
	
	public static void printDashboard(ArrayList<Task> tasks) {
		
		long total = tasks.size();
		
		long open = tasks.stream()
						 .filter(task -> !task.isCompleted())
						 .count();
		
		long completed = tasks.stream()
				              .filter(task -> task.isCompleted())
				              .count();	
		
		long overdue = tasks.stream()
						    .filter(task -> !task.isCompleted() && task.getDueDate().isBefore(LocalDate.now()))
						    .count();
		
		long highPriorityOpen = tasks.stream()
									 .filter(task -> task.getPriority() == Priority.HIGH && !task.isCompleted())
									 .count();
		
	    System.out.println("\n================ TASK TRACKER DASHBOARD ================");
	    System.out.println("Total tasks        : " + total);
	    System.out.println("Open tasks         : " + open);
	    System.out.println("Completed tasks    : " + completed);
	    System.out.println("Overdue tasks      : " + overdue);
	    System.out.println("High priority open : " + highPriorityOpen);
	    System.out.println("=========================================================\n");	}
}
