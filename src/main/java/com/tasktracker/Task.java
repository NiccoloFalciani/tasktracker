package com.tasktracker;

import java.time.LocalDate;

public class Task {
	
	private String title;
	private boolean completed;
	private Priority priority;
	private LocalDate dueDate;
	
	public Task(String title, Priority priority, LocalDate dueDate) {
		this.title = title;
		this.completed = false;	
		this.priority = priority;
		this.dueDate = dueDate;
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
	
	public LocalDate getDueDate() {
		return dueDate;
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
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	// Anzeige in der Konsole
	@Override
	public String toString() {	
		return (completed ? "[x] " + title : "[ ] " + title + " (" + priority + ") - due date: " + dueDate.format(DateUtils.DATE_FORMATTER));
	}

}
