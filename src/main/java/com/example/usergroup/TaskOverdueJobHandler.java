package com.example.usergroup;

import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.jobexecutor.TimerEventJobHandler.TimerJobConfiguration;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

public class TaskOverdueJobHandler  implements JobHandler<TimerJobConfiguration>{

	public static final String TYPE = "overdue-job";
	
	private EscalationService escalationService;
	
	public TaskOverdueJobHandler(EscalationService escalationService) {
		this.escalationService = escalationService;
	}
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void execute(TimerJobConfiguration configuration, ExecutionEntity execution, CommandContext commandContext,
			String tenantId) {
		escalationService.escalate(execution);
	}

	@Override
	public TimerJobConfiguration newConfiguration(String canonicalString) {
		return new TimerJobConfiguration();
	}

	@Override
	public void onDelete(TimerJobConfiguration configuration, JobEntity jobEntity) {
		
	}

}
