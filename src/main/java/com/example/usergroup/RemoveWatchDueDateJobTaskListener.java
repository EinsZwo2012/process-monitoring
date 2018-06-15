package com.example.usergroup;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.extension.reactor.spring.listener.ReactorTaskListener;

public abstract class RemoveWatchDueDateJobTaskListener extends ReactorTaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {	
		ExecutionEntity execution = (ExecutionEntity) delegateTask.getExecution();
		List<JobEntity> jobs = execution.getJobs();
		jobs.stream().filter(job -> job.getJobHandlerType().equals(TaskOverdueJobHandler.TYPE))
						.findAny()
						.ifPresent(job -> job.delete());
	}

}
