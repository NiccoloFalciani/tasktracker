package com.tasktracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ConsoleUI {
	
	public static void printTasks(ArrayList<Task> tasks) {
		if (tasks.isEmpty()) {
			System.out.println("No tasks available!");
			return;
		}
		
		for (Task task : tasks) {
			System.out.println(task);
		}
	}
	
	public static void printSummary(ArrayList<Task> tasks) {

		long total = tasks.size();
		
		long open = tasks.stream()
						 .filter(task -> !task.isCompleted())
						 .count();
		
		long completed = tasks.stream()
				              .filter(task -> task.isCompleted())
				              .count();	
		
		long overdue = tasks.stream()
						    .filter(task -> !task.isCompleted() && task.getDueDate().isBefore(LocalDate.now()))
						    .count();
		
		long highPriorityOpen = tasks.stream()
									 .filter(task -> task.getPriority() == Priority.HIGH && !task.isCompleted())
									 .count();
		System.out.println();
		System.out.println("📊 SYSTEM OVERVIEW");
		System.out.println("-----------------------------------------");
		System.out.println("Total tasks         : " + total);
	    System.out.println("Open tasks          : " + open);
	    System.out.println("Completed tasks     : " + completed);
	    System.out.println("Overdue tasks       : " + overdue);
	    System.out.println("High priority open  : " + highPriorityOpen);		
		
	}
	
	public static void printSlaOverview(ArrayList<Task> tasks) {
		
		long green = tasks.stream()
				          .filter(task -> task.getSlaStatus() == SlaStatus.GREEN)
				          .count();
		
		long yellow = tasks.stream()
		          .filter(task -> task.getSlaStatus() == SlaStatus.YELLOW)
		          .count();		
		
		long red = tasks.stream()
		          .filter(task -> task.getSlaStatus() == SlaStatus.RED)
		          .count();
		
	    long taskTotalAmount = tasks.size();
	    double greenPercent = (double) green/taskTotalAmount * 100;
	    double yellowPercent = (double) yellow/taskTotalAmount * 100;
	    double redPercent = (double) red/taskTotalAmount * 100;
		
		
		System.out.println();
		System.out.println("🚦 SLA HEALTH");
		System.out.println("-----------------------------------------");		
		System.out.println("On track            : " + green + " (" + String.format("%.0f", greenPercent) + "%)");			
		System.out.println("Due soon            : " + yellow + " (" + String.format("%.0f", yellowPercent) + "%)");			
		System.out.println("Overdue             : " + red + " (" + String.format("%.0f", redPercent) + "%)");				
	}
	
	public static void printCriticalTasks(ArrayList<Task> tasks) {
		ArrayList<Task> criticalTasks = (ArrayList<Task>) tasks.stream()
										     .filter(task -> task.getSlaStatus() == SlaStatus.RED)
										     .collect(Collectors.toCollection(ArrayList<Task>::new));

		System.out.println();
		System.out.println("🚨 CRITICAL ITEMS");
		System.out.println("-----------------------------------------");
		
		if (criticalTasks.isEmpty()) {
			System.out.println("No critical items detected");
			return;
		}
		
		for (Task criticalTask : criticalTasks) {
			System.out.println(criticalTask.getTitle() + " " + "[" + criticalTask.getPriority() + "]");
		}
	}	
	
	public static void printTaskTypeOverview(ArrayList<Task> tasks) {
	    
		long standard = tasks.stream()
				       	     .filter(task -> (!(task instanceof ComplianceTask) && !(task instanceof ReportingTask)))
				       	     .count();
		
		long reporting = tasks.stream()
	       	     .filter(task -> (task instanceof ReportingTask))
	       	     .count();	
		
		long compliance = tasks.stream()
	       	     .filter(task -> (task instanceof ComplianceTask))
	       	     .count();
		
	    long taskTotalAmount = tasks.size();
	    double standardPercent = (double) standard/taskTotalAmount * 100;
	    double reportingPercent = (double) reporting/taskTotalAmount * 100;
	    double compliancePercent = (double) compliance/taskTotalAmount * 100;
		
		System.out.println();
		System.out.println("📋 TASK DISTRIBUTION");
		System.out.println("-----------------------------------------");		
		System.out.println("Standard tasks      : " + standard + " (" + String.format("%.0f", standardPercent) + "%)");		
		System.out.println("Reporting tasks     : " + reporting + " (" + String.format("%.0f", reportingPercent)  + "%)");			
		System.out.println("Compliance tasks    : " +compliance + " (" + String.format("%.0f", compliancePercent)  + "%)");			

	}
	
	public static void printComplianceRiskOverview(ArrayList<Task> tasks) {
		ArrayList<ComplianceTask> complianceTasks = tasks.stream()
                                                         .filter(task -> (task instanceof ComplianceTask))
                                                         .map(task -> (ComplianceTask) task)
                                                         .collect(Collectors.toCollection(ArrayList<ComplianceTask>::new));
		
		
		long highRisk = complianceTasks.stream()
				                       .filter(task -> task.getRiskScore() == 100)
				                       .count();
		
		long mediumRisk = complianceTasks.stream()
                .filter(task -> task.getRiskScore() == 50)
                .count();
		
		long lowRisk = complianceTasks.stream()
                .filter(task -> task.getRiskScore() == 10)
                .count();
		
		double averageRisk= 0;
		
		if (complianceTasks.size() > 0) {
			averageRisk = (highRisk * 100 + mediumRisk * 50 + lowRisk * 10)/complianceTasks.size();			
		} 
				
		System.out.println();
		System.out.println("⚠️ COMPLIANCE RISK OVERVIEW");
		System.out.println("-----------------------------------------");	
		System.out.println("High risk compliance tasks      : " + highRisk);				
		System.out.println("Medium risk compliance tasks    : " + mediumRisk);				
		System.out.println("Low risk compliance tasks       : " + lowRisk);				
		System.out.println("Average compliance risk         : " + averageRisk);						
		
	}
	
	public static void printDashboard(ArrayList<Task> tasks) {
		
	    System.out.println("=========================================================");
	    System.out.println("📊 TASK TRACKER DASHBOARD");		
	    System.out.println("=========================================================");
	    printSummary(tasks);
	    printTaskTypeOverview(tasks);
	    printSlaOverview(tasks);
	    printComplianceRiskOverview(tasks);
	    printCriticalTasks(tasks);
	    System.out.println("=========================================================\n");	
	}
	

}
