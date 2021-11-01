package com.example.footballleagues.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Locale;
import java.util.function.Predicate;

@Entity
@Table(name = "teams", schema = "public")
@JsonIgnoreProperties("league")
public class FootballTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String home;
    @ManyToOne()
    @JoinColumn(name = "league_id")
    private League league;

    public FootballTeam() {
    }

    public FootballTeam(String name) {
        this.name = name;
    }

    public static Predicate<FootballTeam> createFilter(String filter) {
        return team -> filter(filter, team);
    }

    public static boolean filter(String filter, FootballTeam team) {
        if (filter == null) {
            return true;
        }
        if (team.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
            return true;
        } else return team.getHome().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
