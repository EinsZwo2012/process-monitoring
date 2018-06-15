package com.example.usergroup;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.incident.DefaultIncidentHandler;
import org.springframework.stereotype.Component;

@Component
public class WatchOverduePlugin extends AbstractProcessEnginePlugin {
	
	  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		  
		  registerJobHandler(processEngineConfiguration);
	  	
		  registerIncidentHandler(processEngineConfiguration);
	  }

	private void registerJobHandler(ProcessEngineConfigurationImpl processEngineConfiguration) {
		processEngineConfiguration.getJobHandlers().put(TaskOverdueJobHandler.TYPE, new TaskOverdueJobHandler(new IncidentEscalationService()));
	}

	private void registerIncidentHandler(ProcessEngineConfigurationImpl processEngineConfiguration) {
		processEngineConfiguration.getIncidentHandlers().put(IncidentEscalationService.INCIDENT_TYPE, 
				  new DefaultIncidentHandler(IncidentEscalationService.INCIDENT_TYPE));
	}
}
