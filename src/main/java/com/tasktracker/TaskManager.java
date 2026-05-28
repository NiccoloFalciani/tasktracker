package com.tasktracker;

import java.util.ArrayList;
import java.io.*;


public class TaskManager {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public TaskManager() {
		tasks = new ArrayList<>();
	}
	
	public void createTask(String title) {
		Task task = new Task(title);
		tasks.add(task);
	}
	
	public ArrayList<Task> getTasks(){
		return tasks;
	}
	
	public boolean markTaskCompleted(int index) {
		if (index < 0 || index >= tasks.size()) {
			return false;
		}
			
		tasks.get(index).markAsCompleted();		
		return true;
	}
	
	public String getTasksAsString() {
		if (tasks.isEmpty()) {
			return "Keine Tasks vorhanden.";
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i < tasks.size(); i++) {
			sb.append(i+1)
			  .append(". ")
			  .append(tasks.get(i))
			  .append("\n");
		}
		
		return sb.toString();
	}
	
	// Speichert den Titel des Tasks und den Flag, ob er abgeschlossen wurde, in der Datei tasks.txt
	public void saveToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
			
			for (Task task : tasks) {
				writer.write(task.getTitle() + "|" + task.isCompleted());
				writer.newLine();
			}
			
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern!");
		}
	}
	
	// Lädt die Informationen zu den Tasks
	public void loadFromFile() {
		
		File file = new File("tasks.txt");
		
		if (!file.exists()) {
			return;
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");  // der Inhalt wird nach "|" zersplittert
				
				String title = parts[0];
				boolean completed = Boolean.parseBoolean(parts[1]);
				
System.out.println("Line: " + line);				
System.out.println("Title: " + title);				
System.out.println("Completed: " + completed);				
				
				Task task = new Task(title);
				
				if (completed) {
					task.markAsCompleted();
				}
				
				tasks.add(task);
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Laden!");
		}

		
	}
	
	
}
