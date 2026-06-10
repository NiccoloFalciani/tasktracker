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
	
	public static void printSummary(ArrayList<Task> tasks) {

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
		System.out.println();
		System.out.println("📊 SYSTEM OVERVIEW");
		System.out.println("Total tasks        : " + total);
	    System.out.println("Open tasks         : " + open);
	    System.out.println("Completed tasks    : " + completed);
	    System.out.println("Overdue tasks      : " + overdue);
	    System.out.println("High priority open : " + highPriorityOpen);		
		
	}
	
	public static void printSlaStatus(ArrayList<Task> tasks) {
		
		long green = tasks.stream()
				          .filter(task -> task.getSlaStatus() == SlaStatus.GREEN)
				          .count();
		
		long yellow = tasks.stream()
		          .filter(task -> task.getSlaStatus() == SlaStatus.YELLOW)
		          .count();		
		
		long red = tasks.stream()
		          .filter(task -> task.getSlaStatus() == SlaStatus.RED)
		          .count();
		
		System.out.println();
		System.out.println("🚦 SLA STATUS");
		System.out.println("GREEN (on track)	: " + green);		
		System.out.println("YELLOW (due soon)	: " + yellow);		
		System.out.println("RED (overdue)		: " + red);				
	}
	
	public static void printDashboard(ArrayList<Task> tasks) {
		
		
	    System.out.println("\n================ TASK TRACKER DASHBOARD ================");
	    printSummary(tasks);
	    printSlaStatus(tasks);
	    System.out.println("=========================================================\n");	
	}
}
