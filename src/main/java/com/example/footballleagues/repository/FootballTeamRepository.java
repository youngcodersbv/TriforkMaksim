package com.example.footballleagues.repository;

import com.example.footballleagues.model.FootballTeam;
import org.springframework.data.repository.CrudRepository;

public interface FootballTeamRepository extends CrudRepository <FootballTeam, Long> {
    Iterable<FootballTeam> findAll();
    FootballTeam findById(long id);

}
