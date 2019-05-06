package com.example.springtest.domain;

import com.example.springtest.repos.GameStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameStats {

    @Id
    private Long id;

    @Column(name = "Pts")
    private int points;
    @Column(name = "Build1")
    private int buildingOne;


    public GameStats(){}

    public GameStats(int points, int buildingOne) {
        this.id = (long)1;
        this.points = points;
        this.buildingOne = buildingOne;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getBuildingOne() {
        return buildingOne;
    }

    public void setBuildingOne(int buildingOne) {
        this.buildingOne = buildingOne;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
