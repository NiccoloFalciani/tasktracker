package com.tasktracker;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.*;
import java.time.LocalDate;


public class TaskManager {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public TaskManager() {
		tasks = new ArrayList<>();
	}
	
	public void createTask(String title, Priority priority, LocalDate dueDate) {
		Task task = new Task(title, priority, dueDate);
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
	
	public boolean deleteTask(int index) {
		if (index < 0 || index >= tasks.size()) {
			return false;
		}
		
		tasks.remove(index);
		saveToFile();
		
		return true;
		
	}
	
	public ArrayList<Task> getOpenTasks() {		
		return (ArrayList<Task>) tasks.stream()
									  .filter(task -> !task.isCompleted())
									  .collect(Collectors.toList());
		
	}
	
	public ArrayList<Task> getCompletedTasks() {		
		return (ArrayList<Task>) tasks.stream()
									  .filter(task -> task.isCompleted())
									  .collect(Collectors.toList());
		
	}
	
	public ArrayList<Task> getOverdueTasks() {
		return (ArrayList<Task>) tasks.stream()
									  .filter(task -> !task.isCompleted() && task.getDueDate().isBefore(LocalDate.now()))
									  .collect(Collectors.toList());
	}
	
	public ArrayList<Task> filterTasks(String query, Priority priority, LocalDate beforeDate){
		
		ArrayList<Task> result = new ArrayList<Task>();
		
		for (Task task : tasks) {
			
			boolean matches = true;
			
			// title filter
			if (query != null && !query.isEmpty()) {
				if (!task.getTitle().toLowerCase().contains(query.toLowerCase())) {
					matches = false;
				}
			}
			
			// priority filter
			if (priority != null) {
				if (task.getPriority() != priority) {
					matches = false;
				}
			}
			
			// due date filter
			if (beforeDate != null) {
				if (task.getDueDate().isAfter(beforeDate)) {
					matches = false;
				}
			}
			
			if (matches) {
				result.add(task);
			}
		}
		
		return result;
		
	}
	
	public boolean updateTaskTitle(int index, String newTitle) {
		if (index < 0 || index >= tasks.size()) {
			return false;
		}
		
		tasks.get(index).setTitle(newTitle);
		saveToFile();
		
		return true;
	}
	
	public boolean updateTaskPriority(int index, Priority priority) {
		if (index < 0 || index >= tasks.size()) {
			return false;
		}
		
		tasks.get(index).setPriority(priority);
		saveToFile();
		
		return true;
	}
	
	public String getTasksAsString() {
		if (tasks.isEmpty()) {
			return "No tasks available.";
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
	
	public void saveToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
			
			for (Task task : tasks) {
				writer.write(task.getTitle() + "|" + task.isCompleted() + "|" + task.getPriority() + "|" + task.getDueDate());
				writer.newLine();
			}
			
		} catch (IOException e) {
			System.out.println("Failed to save!");
		}
	}
	
	public void loadFromFile() {
		
		File file = new File("tasks.txt");
		
		if (!file.exists()) {
			return;
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|"); 
				
				String title = parts[0];
				boolean completed = Boolean.parseBoolean(parts[1]);
				Priority priority = Priority.valueOf(parts[2]);
				LocalDate dueDate = LocalDate.parse(parts[3]);
								
				Task task = new Task(title, priority, dueDate);
				
				if (completed) {
					task.markAsCompleted();
				}
				
				tasks.add(task);
			}
		} catch (IOException e) {
			System.out.println("Failed to save!");
		}

		
	}
	
	
}
