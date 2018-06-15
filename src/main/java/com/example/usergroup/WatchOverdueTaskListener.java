package com.example.usergroup;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.camunda.bpm.extension.reactor.spring.listener.ReactorTaskListener;
import org.springframework.stereotype.Component;

@CamundaSelector(type= TaskListener.EVENTNAME_CREATE)
@Component
public class WatchOverdueTaskListener extends ReactorTaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		
		TimerEntity timer = new TimerEntity();
		
		if(timer.getDuedate() == null) {
			return;
		}
		
		timer.setDuedate(delegateTask.getDueDate());
		timer.setActivityId(delegateTask.getTaskDefinitionKey());
		timer.setProcessInstanceId(delegateTask.getProcessInstanceId());
		timer.setExecution((ExecutionEntity)delegateTask.getExecution());
		timer.setJobHandlerType(TaskOverdueJobHandler.TYPE);
		
		Context.getCommandContext().getJobManager().schedule(timer);
		
	}

}
