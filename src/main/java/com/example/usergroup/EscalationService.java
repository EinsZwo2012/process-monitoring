package com.example.usergroup;

import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

public interface EscalationService {

	void escalate (ExecutionEntity execution);
	
}
