package com.example.usergroup;
import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@WebEndpoint(id="incidents", enableByDefault=true)
public class IncidentActuator {

	@Autowired
	private RuntimeService runtimeService;
	
	@ReadOperation
	public IncidentData incidents () {
		List<String> incidents = runtimeService.createIncidentQuery().list()
					  .stream()
					  .map(incident -> incident.getId())
					  .collect(Collectors.toList());
		
		return new IncidentData(incidents.size(), incidents);
	}
	
	
	@AllArgsConstructor 
	@Getter
	public class IncidentData {
		private long count;
		private List<String> ids;
	}
}
