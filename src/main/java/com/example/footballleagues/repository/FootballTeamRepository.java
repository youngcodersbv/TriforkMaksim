package com.example.footballleagues.repository;

import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.model.League;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FootballTeamRepository extends CrudRepository <FootballTeam, Long> {
    FootballTeam findByName(String arsenal);
}
