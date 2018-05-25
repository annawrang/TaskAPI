package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Query;
import se.dajo.taskBackend.repository.data.IssueDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<IssueDTO, Long> {

    @Query("select i from IssueDTO i order by i.description ")
    List<IssueDTO> findAllOrderedByDescription();
}
