package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;

import static se.dajo.taskBackend.enums.TaskStatus.DONE;
import static se.dajo.taskBackend.enums.TaskStatus.STARTED;

public final class TaskParser {

    public static Task toTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getTaskNumber());
        if (taskDTO.getCreatedDate() != null) {
            task = task.setCreatedDate(taskDTO.getCreatedDate());
        }
        if (taskDTO.getStartedDate() != null) {
            task = task.setStartedDate(taskDTO.getStartedDate());
        }
        if (taskDTO.getDoneDate() != null) {
            task = task.setDoneDate(taskDTO.getDoneDate());
        }
        return task;
    }

    public static TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(task.getDescription(), task.getStatus(), task.getTaskNumber(), task.getCreatedDate());
    }

    public static List<Task> toTaskList(Iterable<TaskDTO> taskDTOS) {
        List<Task> modelTasks = new ArrayList<>();
        taskDTOS.forEach(task -> modelTasks.add(TaskParser.toTask(task)));
        return modelTasks;
    }

    public static TaskDTO updateTaskDTO(TaskDTO taskDTO, Task task) {
        TaskDTO taskDTO1 = new TaskDTO(taskDTO.getId(), task.getDescription(), taskDTO.getStatus(),
                task.getTaskNumber(), task.getCreatedDate());
        if (taskDTO.getStatus() != task.getStatus()) {
            if (task.getStatus() == STARTED) {
                taskDTO1.setStatus(STARTED);
            } else if (task.getStatus() == DONE) {
                taskDTO1.setStartedDate(taskDTO.getStartedDate());
                taskDTO1.setStatus(DONE);
            }
        }
        if (taskDTO.getUser() != null) {
            taskDTO1.setUser(taskDTO.getUser());
        }
        return taskDTO1;
    }
}
