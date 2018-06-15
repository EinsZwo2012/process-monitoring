package com.example.usergroup;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.springframework.stereotype.Component;

@CamundaSelector(type= TaskListener.EVENTNAME_DELETE)
@Component
public class DeleteTaskListener extends RemoveWatchDueDateJobTaskListener{

}
