package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public final class TaskParser {

    public static Task toTask(TaskDTO taskDTO) {
        return new Task(taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getTaskNumber());
    }

    public static TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(task.getDescription(), task.getStatus(), task.getTaskNumber());
    }

    public static List<Task> toTaskList(Iterable<TaskDTO> taskDTOS) {
        List<Task> modelTasks = new ArrayList<>();
        taskDTOS.forEach(task -> {
            modelTasks.add(TaskParser.toTask(task));
        });
        return modelTasks;
    }

    public static TaskDTO updateTaskDTO(TaskDTO taskDTO, Task task) {
        if (taskDTO.getUser() == null) {
            return new TaskDTO(taskDTO.getId(), task.getDescription(), task.getStatus(), task.getTaskNumber());
        } else {
            return new TaskDTO(taskDTO.getId(), task.getDescription(), task.getStatus(), task.getTaskNumber(), taskDTO.getUser());
        }
    }
}
