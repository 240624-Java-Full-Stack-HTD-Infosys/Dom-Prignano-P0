package Controllers;

import Services.TaskService;

public class TaskController {

    // Class setup
    TaskService taskService;
    public TaskController(TaskService taskService) { this.taskService = taskService; }


}
