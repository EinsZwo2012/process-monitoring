package com.example.usergroup;

import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.incident.IncidentContext;
import org.camunda.bpm.engine.impl.incident.IncidentHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

public class IncidentEscalationService implements EscalationService{

	public static final String INCIDENT_TYPE = "overdue";
	
	@Override
	public void escalate(ExecutionEntity execution) {
		
		IncidentContext context = new IncidentContext();
		
		context.setActivityId(execution.getActivityId());
		context.setExecutionId(execution.getId());
		context.setProcessDefinitionId(execution.getProcessDefinitionId());
		
		
		IncidentHandler incidentHandler = Context.getProcessEngineConfiguration().getIncidentHandler(INCIDENT_TYPE);
		incidentHandler.handleIncident(context, "Task is overdue");
	}

}
