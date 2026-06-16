package com.tasktracker;

import java.time.LocalDate;

public class ReportingTask extends Task implements SlaEvaluatable {
	
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
		return TaskType.REPORTING +"  | " + super.toString() 
				+ " | Report: "
				+ (reportName == null ? "N/A" : reportName);
		
	}
	
	@Override
	public SlaStatus getSlaStatus() {
		LocalDate today = LocalDate.now();
		
		if (isCompleted()) {
			return SlaStatus.GREEN;
		}
		
		if (!isCompleted() && getDueDate().isBefore(today)) {
			return SlaStatus.RED;
		}		
		
		if (!isCompleted() && !getDueDate().isAfter(today.plusDays(10))) {
			return SlaStatus.YELLOW;
		}
		
		return SlaStatus.GREEN;		
	}
	
	public String getReportName() {
		return reportName;
	}
}
