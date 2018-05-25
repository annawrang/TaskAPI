package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.data.UserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskDTO, Long> {

    @Query("SELECT MAX(taskNumber) FROM TaskDTO")
    Optional<Long> getHighestTaskNumber();

    TaskDTO findByTaskNumber(Long taskNumber);

    List<TaskDTO> findByDescriptionContaining(String text);

    List<TaskDTO> findByStatus(TaskStatus status);

    @Transactional
    @Modifying
    @Query("update TaskDTO t set t.status = 0 where t.user.id = ?1")
    void setUsersTasksUnstarted(Long id);

    @Query("select t from TaskDTO t where t.user.id = ?1")
    List<TaskDTO> getTaskDTOsInUserDTO(Long id);

    int countTaskDTOByUser(UserDTO userDTO);

    @Query("select t from TaskDTO t order by t.id ")
    List<TaskDTO> findAllOrderedById();

    @Query("select t from TaskDTO t where t.createdDate between ?1 and ?2")
    List<TaskDTO> findAllByUnstartedDate(LocalDate dateFrom, LocalDate dateTo);

    @Query("select t from TaskDTO t where t.startedDate between ?1 and ?2")
    List<TaskDTO> findAllByStartedDate(LocalDate fromDate, LocalDate toDate);

    @Query("select t from TaskDTO t where t.doneDate between ?1 and ?2")
    List<TaskDTO> finAllByDoneDate(LocalDate fromDate, LocalDate toDate);
}
