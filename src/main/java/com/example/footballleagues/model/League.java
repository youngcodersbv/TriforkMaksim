package com.example.footballleagues.model;

import com.example.footballleagues.model.FootballTeam;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "leagues", schema = "public")
public class League {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    private String country;
    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FootballTeam> footballTeamSet;

    public League() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<FootballTeam> getFootballTeamSet() {
        return footballTeamSet;
    }
}
