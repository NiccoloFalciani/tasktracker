package com.tasktracker;

import java.time.LocalDate;

public class ComplianceTask extends Task implements SlaEvaluatable, RiskEvaluatable {
	
	private String regulationId;
	private String riskLevel;
	
	public ComplianceTask(String title, Priority priority, LocalDate dueDate, String regulationId, String riskLevel) {
		super(title, priority, dueDate);
		this.regulationId = regulationId;
		this.riskLevel = riskLevel;
	}
	
	@Override 
	public String toString() {
		return TaskType.COMPLIANCE +" | " + super.toString()
				+ " | Regulation: " + regulationId
				+ " | Risk level: " + riskLevel;
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
		
		if (!isCompleted() && !getDueDate().isAfter(today.plusDays(5))) {
			return SlaStatus.YELLOW;
		}
		
		return SlaStatus.GREEN;			
	}
	
	@Override
	public int getRiskScore() {
		switch (riskLevel.toUpperCase()) {
			case "HIGH":
				return 100;
			case "MEDIUM":
				return 50;
			case "LOW":
				return 10;
			default:
				return 0;
		}
	}
	
	public String getRegulationId() {
		return regulationId;
	}
	
	public String getRiskLevel() {
		return riskLevel;
	}

}
