# Monitor overdue of Camunda User Tasks

# Description

Simple monitoring example of camunda overdue camunda user tasks.

With every active user task a camunda job is persisted which is trigger if due date of user task is reached and it is not completed or deleted.
If job is triggered a custom job handler is called delegates to an escalation service.
Concrete escalation service creates an incident of type 'overdue' in database which is associated to user task.
A spring actuator endpoint makes these incidents consumable for different monitoring applications.

# How to use

1. Check out the repository and start application. Camunda webapps are reachable under http://localhost:8082.
2. Start a new process instance in tasklist.
3. A job will be created which can be accessed via REST. Url is http://localhost:8082/rest/engine/default/job.
4. Copy job id and execute it via REST . URL is http://localhost:8082/rest/engine/default/job/{jobId}/execute`.
5. A incident was created. You can see it in cockpit or in actuator endpoint. URL of edpoint is http://localhost:8080/actuator/incidents 