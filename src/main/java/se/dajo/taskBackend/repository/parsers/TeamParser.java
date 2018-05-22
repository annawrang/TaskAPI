package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Team;
import se.dajo.taskBackend.repository.data.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public final class TeamParser {

    public static Team toTeam(TeamDTO teamDTO) {
        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }

    public static List<Team> toTeamList(List<TeamDTO> teamDTOS) {
        List<Team> teams = new ArrayList<>();
        teamDTOS.forEach(teamDTO -> teams.add(toTeam(teamDTO)));
        return teams;
    }

    public static TeamDTO updateTeamDTO(TeamDTO teamDTO, Team team) {
        return new TeamDTO(teamDTO.getId(), team.getTeamName(), team.getStatus());
    }
}

