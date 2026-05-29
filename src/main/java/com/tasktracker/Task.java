package com.tasktracker;

public class Task {
	
	private String title;
	private boolean completed;
	private Priority priority;
	
	public Task(String title, Priority priority) {
		this.title = title;
		this.completed = false;	
		this.priority = priority;
	}
	
	// Getter
	public String getTitle() {
		return title;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	// Setter
	public void markAsCompleted() {
		this.completed = true;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	// Anzeige in der Konsole
	@Override
	public String toString() {
		return (completed ? "[x] " + title : "[ ] " + title + " (" + priority + ")");
	}

}
