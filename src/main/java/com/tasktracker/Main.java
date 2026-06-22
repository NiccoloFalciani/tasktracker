package com.tasktracker;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		TaskManager taskManager = new TaskManager();
		Scanner scanner = new Scanner(System.in);
		
		taskManager.loadFromFile();
		
		ConsoleUI.printDashboard(taskManager.getTasks());
		
		while (true) {
			
			System.out.println("\n=== TASK TRACKER ===");
			System.out.println(" 1 - Add task");
			System.out.println(" 2 - Show all tasks");
			System.out.println(" 3 - Mark task as completed");
			System.out.println(" 4 - Delete task");
			System.out.println(" 5 - Show open tasks");
			System.out.println(" 6 - Show completed tasks");
			System.out.println(" 7 - Rename task");
			System.out.println(" 8 - Change task priority");
			System.out.println(" 9 - Change due date");
			System.out.println("10 - Show overdue tasks");
			System.out.println("11 - Search tasks");
			System.out.println("12 - Exit application");
			System.out.println("Selection: ");
			
			int choice = scanner.nextInt();
			scanner.nextLine(); 
			
			switch (choice) {
			
				case 1:	
					String reportName = null;
					String regulationId = null;
					String riskLevel = null;
										;
					System.out.println("Task title: ");
					String title = scanner.nextLine();
					
					Priority priority = readPriority(scanner, false);
					LocalDate dueDate = readDueDate(scanner, false);
					
					TaskType taskType = readTaskType(scanner);
					
					if (taskType == TaskType.REPORTING) {
						
						System.out.println("Report name: ");
						reportName = scanner.nextLine();
						
					} else if (taskType == TaskType.COMPLIANCE) {
						
						System.out.println("Regulation ID: ");
						regulationId = scanner.nextLine();
						
						System.out.println("Risk level:");
						riskLevel = scanner.nextLine();
					}					
							
					taskManager.createTask(taskType, title, priority, dueDate, reportName, regulationId, riskLevel);
					break;
					
				case 2:
					System.out.println(taskManager.getTasksAsString());
					break;
					
				case 3:
					System.out.println("Task number: ");
					int index = scanner.nextInt();
					scanner.nextLine();
					
					boolean isTaskCompletedCorrectly = taskManager.markTaskCompleted(index - 1);
					
					if (!isTaskCompletedCorrectly) {
						System.out.println("Invalid index!");
					}
					break;
					
				case 4:
					System.out.println("Task number: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					boolean wasTaskDeletedCorrectly = taskManager.deleteTask(index - 1);
					
					if (!wasTaskDeletedCorrectly) {
						System.out.println("Invalid index!");
					}
					break;
					
				case 5:
					
					ArrayList<Task> openTasks = taskManager.getOpenTasks();					
					ConsoleUI.printTasks(openTasks);															
					break;
					
				case 6:
					
					ArrayList<Task> completedTasks = taskManager.getCompletedTasks();					
					ConsoleUI.printTasks(completedTasks);					
					break;
					
				case 7:
					System.out.println("Task number: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("New task title: ");
					String newTitle = scanner.nextLine();					
					
					boolean wasTaskRenamedCorrectly = taskManager.updateTaskTitle(index - 1, newTitle);
					
					if (!wasTaskRenamedCorrectly) {
						System.out.println("Invalid index!");
					}					
					
					break;
					
				case 8:
					System.out.println("Task number: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					priority = readPriority(scanner, false);
					
					boolean wasTaskPriorityChangedCorrectly = taskManager.updateTaskPriority(index - 1, priority);

					if (!wasTaskPriorityChangedCorrectly) {
						System.out.println("Invalid index!");
					}	
					break;
					
				case 9:
					System.out.println("Task number: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					dueDate = readDueDate(scanner, false);
					
					boolean wasDueDateChangedCorrectly = taskManager.updateDueDate(index - 1, dueDate);
					
					if (!wasDueDateChangedCorrectly) {
						System.out.println("Invalid index!");
					}
					break;
				
				case 10:

					ArrayList<Task> overdueTasks = taskManager.getOverdueTasks();					
					ConsoleUI.printTasks(overdueTasks);														
					break;
					
				case 11:
					System.out.println("Search text (or empty): ");
					String query = scanner.nextLine();				
					
					priority = readPriority(scanner, true);
					
					dueDate = readDueDate(scanner, true);
					
					ArrayList<Task> filteredTasks = taskManager.filterTasks(query, priority, dueDate);
					ConsoleUI.printTasks(filteredTasks);									
					break;
					
				case 12:
					taskManager.saveToFile();
					System.out.println("Program terminated");
					scanner.close();
					return;
					
				default:
					System.out.println("Invalid input provided!");
			}
 		}		
	}
	
	private static Priority readPriority(Scanner scanner, boolean optional) {
		Priority priority = null;
		
		while (priority == null) {
			
			System.out.println(optional 
					? "Priority (LOW, MEDIUM, HIGH or empty): " 
					: "Priority (LOW, MEDIUM or HIGH): ");
			
			String input = scanner.nextLine().toUpperCase();
			
			if (optional && input.isEmpty()) {
				return null;
			}
			
			try {
				priority = Priority.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid priority!");
			}
		}
		
		return priority;
	}
	
	private static LocalDate readDueDate(Scanner scanner, boolean optional) {
		LocalDate dueDate = null;
		
		while (dueDate == null) {
			
	        System.out.print(optional
	                ? "Due date (DD.MM.YYYY or empty): "
	                : "Due date (DD.MM.YYYY): ");
			
			String input = scanner.nextLine();
			
			if (optional && input.isEmpty()) {
				return null;
			}
			
			try {
				dueDate = LocalDate.parse(input, DateUtils.DATE_FORMATTER);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid due date!");
			}
		}		
		
		return dueDate;
	}
	
	private static TaskType readTaskType(Scanner scanner) {
		TaskType taskType = null;
		
		while (taskType == null) {
			System.out.println("Task type:");
			System.out.println("1 - STANDARD");
			System.out.println("2 - REPORTING");
			System.out.println("3 - COMPLIANCE");
			
			String input = scanner.nextLine();
			
			switch (input) {
				case "1":
					taskType = TaskType.STANDARD;
					break;
				case "2":
					taskType = TaskType.REPORTING;
					break;
				case "3":
					taskType = TaskType.COMPLIANCE;
					break;
					
				default:
					System.out.println("Invalid input!");
			}			
		}
		
		return taskType;
			
	}

}
