package Services;

import Daos.TaskDao;

public class TaskService {

    // Class setup
    TaskDao taskDao;
    public TaskService(TaskDao taskDao) { this.taskDao = taskDao; }



}
