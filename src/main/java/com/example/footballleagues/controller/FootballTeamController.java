package com.example.footballleagues.controller;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.exceptions.LeagueNotFoundException;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.repository.FootballTeamRepository;
import com.example.footballleagues.repository.LeagueRepository;
import com.example.footballleagues.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/teams")
public class FootballTeamController {
    private final FootballTeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final TeamService service;

    @Autowired
    public FootballTeamController(FootballTeamRepository teamRepository, LeagueRepository leagueRepository, TeamService service) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
        this.service = service;
    }

    @GetMapping
    public String teams(Model model) {
        Iterable iter = teamRepository.findAll();
        model.addAttribute("team", iter);
        return "teams";
    }

    @PostMapping
    public String postTeam(@Valid @ModelAttribute TeamDto teamDto) {
        leagueRepository.findById(teamDto.getLeagueId()).orElseThrow(()
                -> new LeagueNotFoundException("id - " + teamDto.getLeagueId()));

        FootballTeam team = new FootballTeam();

        service.addOrUpdateTeam(teamDto, team);

        return "redirect:/teams";
    }

    @PostMapping("/specificTeam/{id}")
    public String updateTeam(@PathVariable("id") Long id,
                             @Valid @ModelAttribute TeamDto teamDto) {

        leagueRepository.findById(teamDto.getLeagueId()).orElseThrow(()
                -> new LeagueNotFoundException("id - " + teamDto.getLeagueId()));

        FootballTeam team = teamRepository.findById(id).get();
        service.addOrUpdateTeam(teamDto, team);
        return "redirect:/teams";
    }

    @GetMapping("/specificTeam")
    public String showUpdateForm(@RequestParam(value = "id", required = false) Long id,
                                 Model model) {
        FootballTeam team = teamRepository.findById(id).get();
        model.addAttribute("team", team);
        return "specificTeam";
    }

    @GetMapping("/delete")
    public String deleteTeam(@RequestParam("id") Long id) {
        FootballTeam team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));
        teamRepository.delete(team);
        return "redirect:/teams";
    }


}
