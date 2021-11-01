package com.example.footballleagues.service;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.exceptions.LeagueNotFoundException;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;

import static com.example.footballleagues.model.League.createFilter;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    private LeagueRepository leagueRepository;


    public League[] getAllLeagueInJsonService(String filter) {
        Iterable<League> commutes = leagueRepository.findAll();

        List<League> result = new ArrayList();
        StreamSupport.stream(commutes.spliterator(),false)
                .filter(createFilter(filter))
                .forEach(result::add);

        return result.toArray(new League[result.size()]);
    }

    public League getLeagueById(long id) {
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

    @Override
    public League[] getAllLeagues() {
        List<League> list = new ArrayList<>();
        Iterable<League> all = leagueRepository.findAll();
        StreamSupport.stream(all.spliterator(), false)
                .forEach(list::add);
        return list.toArray(new League[list.size()]);
    }
}
