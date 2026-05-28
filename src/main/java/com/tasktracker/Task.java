package com.tasktracker;

public class Task {
	
	private String title;
	private boolean completed;
	
	public Task(String title) {
		this.title = title;
		this.completed = false;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void markAsCompleted() {
		this.completed = true;
	}
	
	@Override
	public String toString() {
		return (completed ? "[x] " + title : "[ ] " + title);
	}

}
