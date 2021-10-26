package com.example.footballleagues.controller;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.exceptions.TeamNotFoundException;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.repository.FootballTeamRepository;
import com.example.footballleagues.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping(path = "/json/teams")
@RequiredArgsConstructor
public class FootballTeamJsonController {

    private final FootballTeamRepository teamRepository;
    private final TeamService teamService;

    @GetMapping
    public FootballTeam[] getAllTeamInJson(@RequestParam(value = "filter", required = false) String filter) {
        return teamService.getAllTeamInJsonService(filter);
    }

    @GetMapping("/{id}")
    public FootballTeam getTeamById(@PathVariable("id") long id) {
       FootballTeam team = teamRepository.findById(id).orElseThrow(()
                -> new TeamNotFoundException("id - " + id));
        return team;
    }

    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable("id") long id) {
        teamService.deleteTeamByIdService(id);
    }

    @PutMapping("/{id}")
    public void updateTeamById(@PathVariable("id") long id, @Valid @RequestBody TeamDto teamDto) {
      teamService.updateTeam(id,teamDto);
    }
}
