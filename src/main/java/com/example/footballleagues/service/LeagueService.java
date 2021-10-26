package com.example.footballleagues.service;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.exceptions.LeagueNotFoundException;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;

import static com.example.footballleagues.model.League.createFilter;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;


        public League[] getAllLeagueInJsonService(@RequestParam(value = "filter",required = false) String filter) {
            Iterable<League> commutes = leagueRepository.findAll();

            List<League> result = new ArrayList();
            StreamSupport.stream(commutes.spliterator(),false)
                    .filter((League league) -> {
                        if(filter == null) {
                            return true;
                        }
                        if(league.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                            return true;
                        } else if(league.getCountry().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    })
                    .filter((League league) -> {
                        return League.filter(filter, league);
                    })
                    .filter(commute -> League.filter(filter, commute))
                    .filter(createFilter(filter))
                    .forEach(result::add);

            return result.toArray(new League[result.size()]);

    }

    public League getLeagueById(@PathVariable("id") long id) {
       League league = leagueRepository.findById(id).orElseThrow(()
                       -> new LeagueNotFoundException("id - " + id));
        return league;
    }

    public void deleteLeagueById(long id) {
        League league=  leagueRepository.findById(id).orElseThrow(()
                -> new LeagueNotFoundException("id - " + id));
            leagueRepository.delete(league);

    }

    public void updateLeague(long id, LeagueDto leagueDto) {
        League league = leagueRepository.findById(id).orElseThrow(()
                -> new LeagueNotFoundException("id - " + id));
        league.setName(leagueDto.getName());
        league.setCountry(leagueDto.getCountry());
        leagueRepository.save(league);
    }
}
