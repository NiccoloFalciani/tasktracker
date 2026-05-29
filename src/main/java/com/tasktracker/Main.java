package com.tasktracker;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		Scanner scanner = new Scanner(System.in);
		
		// Lädt Infos zu den Tasks aus der .txt-Datie
		taskManager.loadFromFile();
		
		while (true) {
			
			System.out.println("\n=== TASK TRACKER ===");
			System.out.println("1 - Task hinzufügen");
			System.out.println("2 - Tasks anzeigen");
			System.out.println("3 - Task erledigen");
			System.out.println("4 - Task Löschen");
			System.out.println("5 - Offene Tasks anzeigen");
			System.out.println("6 - Erledigte Tasks anzeigen");
			System.out.println("7 - Task umbenennen");
			System.out.println("8 - Task-Priorität ändern");
			System.out.println("9 - Beenden");
			System.out.println("Auswahl: ");
			
			int choice = scanner.nextInt();
			scanner.nextLine(); // wichtig wegen Enter-Problem
			
			switch (choice) {
			
				case 1:
					System.out.println("Task-Titel: ");
					String title = scanner.nextLine();
					
					Priority priority = readPriority(scanner);
							
					taskManager.createTask(title, priority);
					break;
					
				case 2:
					System.out.println(taskManager.getTasksAsString());
					break;
					
				case 3:
					System.out.println("Task-Nummer: ");
					int index = scanner.nextInt();
					scanner.nextLine();
					
					boolean isTaskCompletedCorrectly = taskManager.markTaskCompleted(index - 1);
					
					if (!isTaskCompletedCorrectly) {
						System.out.println("Ungültiger Index!");
					}
					break;
					
				case 4:
					System.out.println("Task-Nummer: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					boolean wasTaskDeletedCorrectly = taskManager.deleteTask(index - 1);
					
					if (!wasTaskDeletedCorrectly) {
						System.out.println("Ungültiger Index!");
					}
					break;
					
				case 5:
					ArrayList<Task> openTasks = taskManager.getOpenTasks();
					
					if (openTasks.isEmpty()) {
						System.out.println("Keine offenen Tasks vorhanden!");
					}
										
					
					for (int i=0; i<openTasks.size(); i++) {
						System.out.println(i+1 + ". " + openTasks.get(i).getTitle());
					}
					break;
					
				case 6:
					ArrayList<Task> completedTasks = taskManager.getCompletedTasks();
					
					if (completedTasks.isEmpty()) {
						System.out.println("Keine abgeschlossenen Tasks vorhanden!");
					}
					
					for (int i=0; i<completedTasks.size(); i++) {
						System.out.println(i+1 + ". " + completedTasks.get(i).getTitle());
					}
					break;
					
				case 7:
					System.out.println("Task-Nummer: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("Neuer Task-Titel: ");
					String newTitle = scanner.nextLine();					
					
					boolean wasTaskRenamedCorrectly = taskManager.updateTaskTitle(index - 1, newTitle);
					
					if (!wasTaskRenamedCorrectly) {
						System.out.println("Ungültiger Index!");
					}					
					
					break;
					
				case 8:
					System.out.println("Task-Nummer: ");
					index = scanner.nextInt();
					scanner.nextLine();
					
					priority = readPriority(scanner);
					
					boolean wasTaskPriorityChangedCorrectly = taskManager.updateTaskPriority(index - 1, priority);

					if (!wasTaskPriorityChangedCorrectly) {
						System.out.println("Ungültiger Index!");
					}	
					break;
					
				case 9:
					// Tasks vor dem Beenden speichern
					taskManager.saveToFile();
					System.out.println("Programm beendet");
					scanner.close();
					return;
					
				default:
					System.out.println("Ungültige Eingabe!");
			}
 		}		
	}
	
	private static Priority readPriority(Scanner scanner) {
		Priority priority = null;
		
		while (priority == null) {
			System.out.println("Priorität (kann LOW, MEDIUM oder HIGH sein):");
			String input = scanner.nextLine().toUpperCase();
			
			try {
				priority = Priority.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.println("Ungültige Priorität!");
			}
		}
		
		return priority;
	}

}
