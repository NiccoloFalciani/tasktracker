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
			System.out.println("4 - Beenden");
			System.out.println("Auswahl: ");
			
			int choice = scanner.nextInt();
			scanner.nextLine(); // wichtig wegen Enter-Problem
			
			switch (choice) {
			
				case 1:
					System.out.println("Task-Titel: ");
					String title = scanner.nextLine();
					taskManager.createTask(title);
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
					// Tasks vor dem Beenden speichern
					taskManager.saveToFile();
					System.out.println("Programm beendet");
					scanner.close();
					return;
					
				default:
					System.out.println("Ungültige Eingabe!");
			}
; 		}		
	}

}
