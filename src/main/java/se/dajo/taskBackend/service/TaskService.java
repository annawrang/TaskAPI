package se.dajo.taskBackend.service;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.IssueRepository;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.resource.param.TaskParam;
import se.dajo.taskBackend.service.exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    private final UserService userService;
    private AtomicLong taskNumbers;
    private final int maximumAmountOfTasksForUser = 5;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, IssueRepository issueRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
        this.userService = userService;
    }

    public Task saveTask(Task task) {
        if (task.getTaskNumber() == null) {
            this.taskNumbers = new AtomicLong(this.taskRepository.getHighestTaskNumber().orElse(1000000000L));
            task = task.setTaskNumber(taskNumbers.incrementAndGet());
            return TaskParser.toTask(taskRepository.save(TaskParser.toTaskDTO(task)));
        } else {
            TaskDTO oldTaskDTO = taskRepository.findByTaskNumber(task.getTaskNumber());
            if (oldTaskDTO == null) {
                throw new InvalidTaskNumberException();
            }
            oldTaskDTO = TaskParser.updateTaskDTO(oldTaskDTO, task);
            return TaskParser.toTask(taskRepository.save(oldTaskDTO));
        }
    }

    public Task updateTask(Long userNumber, Long taskNumber) {
        validateRoomForTask(userNumber);
        validateUserActiveStatus(userNumber);
        getTask(taskNumber);
        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        if (taskDTO == null) {
            throw new InvalidTaskNumberException();
        }
        UserDTO userDTOtoSave = userRepository.findUserDTOByUserNumber(userNumber);
        if (taskDTO.getUser() != null) {
            if (userNumber.equals(taskDTO.getUser().getUserNumber())) {
                userDTOtoSave = null;
            }
        }
        TaskDTO taskDTOtoSave = new TaskDTO(taskDTO.getId(), taskDTO.getDescription(),
                taskDTO.getStatus(), taskDTO.getTaskNumber(), userDTOtoSave, taskDTO.getCreatedDate());

        return TaskParser.toTask(taskRepository.save(taskDTOtoSave));
    }

    private void validateUserActiveStatus(Long userNumber) {

        User user = userService.getUser(userNumber);
        if (user.getStatus().equals(Status.INACTIVE)) {
            throw new InactiveUserException();
        }
    }

    private void validateRoomForTask(Long userNumber) {
        int amountOfTasksForUser = taskRepository.countTaskDTOByUser(userRepository.findUserDTOByUserNumber(userNumber));
        if (amountOfTasksForUser >= maximumAmountOfTasksForUser) {
            throw new OverworkedUserException();
        }
    }

    private Task getTask(Long taskNumber) {
        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        if (taskDTO == null) {
            throw new InvalidTaskNumberException();
        }
        return TaskParser.toTask(taskDTO);
    }

    private List<Task> getTaskByDescription(String text) {
        List<TaskDTO> taskDTOs = taskRepository.findByDescriptionContaining(text);
        if (taskDTOs.isEmpty()) {
            throw new InvalidDescriptionException(text);
        }
        return TaskParser.toTaskList(taskDTOs);
    }

    private List<Task> getTaskByStatus(TaskStatus status) {
        List<TaskDTO> taskDTOs = taskRepository.findByStatus(status);
        if (taskDTOs.isEmpty()) {
            throw new InvalidStatusException(status);
        }
        return TaskParser.toTaskList(taskDTOs);
    }

    private List<Task> getTasksWithIssue() {
        List<IssueDTO> issueDTOs = Lists.newArrayList(issueRepository.findAll());
        List<Task> tasksList = new ArrayList<>();
        for (IssueDTO issueDTO : issueDTOs) {
            tasksList.add(TaskParser.toTask(issueDTO.getTaskDTO()));
        }
        Set<Task> taskSet = new HashSet<>(tasksList);
        return new ArrayList<>(taskSet);
    }

    public List<Task> getTasks(TaskParam param) {
        if (param.text != null) {
            return getTaskByDescription(param.text);
        } else if (param.status != null) {
            TaskStatus status = getParamStatus(param.status);
            if (param.fromDate != null && param.toDate != null) {
                return getTasksByDate(status, param.fromDate, param.toDate);
            }
            return getTaskByStatus(status);
        } else if (param.issue != null) {
            return getTasksWithIssue();
        } else if (param.display != null && param.start != null) {
            List<TaskDTO> taskDTOS = taskRepository.findAllOrderedById();
            return TaskParser.toTaskList(createListPaged(taskDTOS, param.start, param.display));
        }
        return TaskParser.toTaskList(taskRepository.findAll());
    }

    private Iterable<TaskDTO> createListPaged(List<TaskDTO> taskDTOS, Integer start, Integer display) {
        if (start <= taskDTOS.size()) {
            List<TaskDTO> tasksPaged = new ArrayList<>();
            for (int i = start - 1; i < start - 1 + display; i++) {
                if (taskDTOS.size() > i) {
                    tasksPaged.add(taskDTOS.get(i));
                }
            }
            return tasksPaged;
        } else {
            throw new InvalidPagingRequestException("");
        }
    }

    private List<Task> getTasksByDate(TaskStatus status, String fromDateIn, String toDateIn) {
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(fromDateIn);
            toDate = LocalDate.parse(toDateIn);
        } catch (Exception e) {
            throw new InvalidDateInputException();
        }
        List<TaskDTO> taskDTOS = new ArrayList<>();
        if(status == TaskStatus.UNSTARTED) {
            taskDTOS = taskRepository.findAllByUnstartedDate(fromDate, toDate);
        } else if(status == TaskStatus.STARTED){
            taskDTOS = taskRepository.findAllByStartedDate(fromDate, toDate);
        } else if(status == TaskStatus.DONE){
            taskDTOS = taskRepository.finAllByDoneDate(fromDate, toDate);
        }
        return TaskParser.toTaskList(taskDTOS);
    }

    private TaskStatus getParamStatus(String status) {
        switch (status) {
            case "unstarted":
                return TaskStatus.UNSTARTED;
            case "started":
                return TaskStatus.STARTED;
            case "done":
                return TaskStatus.DONE;
            case "annulled":
                return TaskStatus.ANNULLED;
            default:
                throw new InvalidTaskRequestException();
        }
    }

}
