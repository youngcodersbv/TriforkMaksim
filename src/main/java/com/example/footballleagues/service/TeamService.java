package com.example.footballleagues.service;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.exceptions.LeagueNotFoundException;
import com.example.footballleagues.exceptions.TeamNotFoundException;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.FootballTeamRepository;
import com.example.footballleagues.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;

import static com.example.footballleagues.model.FootballTeam.createFilter;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final FootballTeamRepository teamRepository;
    private final LeagueRepository leagueRepository;


    public FootballTeam[] getAllTeamInJsonService(@RequestParam(value = "filter",required = false) String filter) {
        Iterable<FootballTeam> commutes = teamRepository.findAll();

        List<FootballTeam> result = new ArrayList();
        StreamSupport.stream(commutes.spliterator(),false)
                .filter((FootballTeam team) -> {
                    if(filter == null) {
                        return true;
                    }
                    if(team.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                        return true;
                    } else if(team.getHome().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .filter((FootballTeam team) -> {
                    return FootballTeam.filter(filter, team);
                })
                .filter(commute -> FootballTeam.filter(filter, commute))
                .filter(createFilter(filter))
                .forEach(result::add);

        return result.toArray(new FootballTeam[result.size()]);
    }

    public void deleteTeamByIdService(long id) {
      FootballTeam team = teamRepository.findById(id).orElseThrow(()
                -> new TeamNotFoundException("id - " + id));
        teamRepository.delete(team);

    }


    public void updateTeam(long id, TeamDto teamDto) {
        FootballTeam team = teamRepository.findById(id).orElseThrow(()
                -> new TeamNotFoundException("id - " + id));
        team.setName(teamDto.getName());
        team.setHome(teamDto.getHome());
        League league = leagueRepository.findById(teamDto.getLeagueId()).orElseThrow(()
                -> new LeagueNotFoundException("id - " + teamDto.getLeagueId()));
        team.setLeague(league);
        teamRepository.save(team);
    }

    public void addOrUpdateTeam(@ModelAttribute TeamDto teamDto, FootballTeam team){
        League league = leagueRepository.findById(teamDto.getLeagueId()).orElseThrow(()
                -> new LeagueNotFoundException("id - " + teamDto.getLeagueId()));
        if(!teamDto.getName().equals("")) team.setName(teamDto.getName());
        if(!teamDto.getHome().equals("")) team.setHome(teamDto.getHome());
        team.setLeague(league);
        league.getFootballTeamSet().add(team);
        leagueRepository.save(league);
    }
}
