package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Query;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.data.TeamDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.dajo.taskBackend.repository.data.UserDTO;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<TeamDTO, Long> {

    TeamDTO findTeamDTOByTeamName(String teamName);

    @Query("select u from UserDTO u where u.team.id = ?1")
    List<UserDTO> getUserDTOSInTeamDTO(Long teamId);

    @Query("select t from TaskDTO t where t.user.team.id = ?1")
    List<TaskDTO> getTaskDTOSInTeamDTO(Long id);
}
