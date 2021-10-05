package com.example.footballleagues.controller;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.FootballTeamRepository;
import com.example.footballleagues.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/teams")
public class FootballTeamController {
    @Autowired
    private FootballTeamRepository teamRepository;
    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping
    public String teams(Model model) {
        Iterable iter = teamRepository.findAll();
        model.addAttribute("team", iter);
        return "teams";
    }

    @PostMapping
    public String postTeam(@ModelAttribute TeamDto teamDto){
        Optional<League> optionalLeague = leagueRepository.findById(teamDto.getLeagueId());
        optionalLeague.ifPresent((League league) -> {

            FootballTeam team= new FootballTeam();
            team.setName(teamDto.getName());
            team.setHome(teamDto.getHome());
            team.setLeague(league);
            league.getFootballTeamSet().add(team);
            leagueRepository.save(league);
        });
        return "redirect:/teams";
    }
}
