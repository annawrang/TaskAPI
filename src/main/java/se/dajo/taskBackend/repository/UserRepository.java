package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Query;
import se.dajo.taskBackend.repository.data.TeamDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, Long> {

    List<UserDTO> findByFirstNameAndSurNameAndUserNumber(String firstName, String surName, Long userNumber);

    List<UserDTO> findByFirstNameAndSurName(String firstName, String surName);

    List<UserDTO> findByFirstNameAndUserNumber(String firstName, Long userNumber);

    List<UserDTO> findByFirstName(String firstName);

    List<UserDTO> findBySurName(String surName);

    List<UserDTO> findBySurNameAndUserNumber(String surName, Long userNumber);

    List<UserDTO> findByUserNumber(Long userNumber);

    UserDTO findUserDTOByUserNumber(Long userNumber);

    @Query("SELECT MAX(userNumber) FROM UserDTO")
    Optional<Long> getHighestUserNumber();

    int countUserDTOByTeam(TeamDTO teamDTO);
}
