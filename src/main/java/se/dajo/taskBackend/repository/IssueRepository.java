package se.dajo.taskBackend.repository;

import se.dajo.taskBackend.repository.data.IssueDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<IssueDTO, Long> {

}
