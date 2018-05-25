package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public final class IssueParser {
    public static IssueDTO toIssueDTO(Issue issue, TaskDTO taskDTO) {
        return new IssueDTO(issue.getDescription(), taskDTO);
    }

    public static Issue parseIssueDTOToIssue(IssueDTO issueDTO) {
        return new Issue(issueDTO.getDescription());
    }

    public static List<Issue> toIssueList(List<IssueDTO> issueDTOS){
        List<Issue> issues = new ArrayList<>();
        issueDTOS.forEach(i -> issues.add(parseIssueDTOToIssue(i)));
        return issues;
    }
}
