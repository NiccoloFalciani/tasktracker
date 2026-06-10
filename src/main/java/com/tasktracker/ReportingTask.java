package com.tasktracker;

import java.time.LocalDate;

public class ReportingTask extends Task {
	
	private String reportName;

	public ReportingTask(	String title,
							Priority priority,
							LocalDate dueDate,
							String reportName) {
		super(title, priority, dueDate);
		this.reportName = reportName;
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ " | Report: "
				+ (reportName == null ? "N/A" : reportName);
		
	}
}
