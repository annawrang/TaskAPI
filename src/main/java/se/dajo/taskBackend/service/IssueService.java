package se.dajo.taskBackend.service;

import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.IssueParser;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.service.exception.InvalidStatusException;
import se.dajo.taskBackend.service.exception.InvalidTaskNumberException;

import static se.dajo.taskBackend.enums.TaskStatus.UNSTARTED;

@Service
public final class IssueService {

    private final IssueRepository issueRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @Autowired
    public IssueService(IssueRepository issueRepository, TaskRepository taskRepository, TaskService taskService) {
        this.issueRepository = issueRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    public Issue saveIssue(Issue issue, Long taskNumber) {

        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        if (taskDTO == null) {
            throw new InvalidTaskNumberException();
        }
        if (taskDTO.getStatus() != TaskStatus.DONE) {
            throw new InvalidStatusException();
        }
        Task task = TaskParser.toTask(taskDTO);
        IssueDTO issueDTO = IssueParser.toIssueDTO(issue, taskDTO);
        issueDTO = issueRepository.save(issueDTO);
        task.setStatus(UNSTARTED);
        taskService.saveTask(task);
        return IssueParser.parseIssueDTOToIssue(issueDTO);
    }
}
