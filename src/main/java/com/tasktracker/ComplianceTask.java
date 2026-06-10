package com.tasktracker;

import java.time.LocalDate;

public class ComplianceTask extends Task {
	
	private String regulationId;
	private String riskLevel;
	
	public ComplianceTask(String title, Priority priority, LocalDate dueDate, String regulationId, String riskLevel) {
		super(title, priority, dueDate);
		this.regulationId = regulationId;
		this.riskLevel = riskLevel;
	}
	
	@Override 
	public String toString() {
		return super.toString()
				+ " | Regulation: " + regulationId
				+ " | Risk level: " + riskLevel;
	}

}
